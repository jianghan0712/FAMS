package com.purefun.fams.framework.core.initor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.kafka.annotation.KafkaListener;

import com.purefun.fams.framework.core.annotation.FAMSEventListener;
import com.purefun.fams.framework.core.communication.listener.IEventListener;

/**
 * 对所有监听器IEventListener的bean进行process
 * 
 * @Classname: FAMSListenerBeanPostPocessor
 * @Description:
 * @author jiang
 * @date 2020-02-13 23:52:09
 */
//@Component
public class FAMSListenerBeanPostPocessor implements BeanPostProcessor, Ordered, BeanFactoryAware {
	private static final Logger logger = LogManager.getLogger(FAMSListenerBeanPostPocessor.class);
	private BeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	@Override
	public int getOrder() {
		return LOWEST_PRECEDENCE - 100;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
		if (bean instanceof IEventListener) {
			logger.info("find IEventListener {}", beanName);
			try {
				Method[] methods = bean.getClass().getMethods();

				for (Method eachMethods : methods) {
					FAMSEventListener famsListenerAnno = AnnotationUtils.findAnnotation(eachMethods,
							FAMSEventListener.class);
					if (famsListenerAnno == null)
						continue;
					KafkaListener kafkaListenerAnno = AnnotationUtils.getAnnotation(famsListenerAnno,
							KafkaListener.class);
					Map<String, Object> famsValues = AnnotationUtils.getAnnotationAttributes(famsListenerAnno);

					InvocationHandler invocationHandler = Proxy.getInvocationHandler(kafkaListenerAnno);
					Field kafkaListenerField = invocationHandler.getClass().getDeclaredField("memberValues");
					kafkaListenerField.setAccessible(true);
					Map<String, Object> kafkaValues = (Map<String, Object>) kafkaListenerField.get(invocationHandler);
					for (Map.Entry<String, Object> eachField : kafkaValues.entrySet()) {
						if (famsValues.get(eachField.getKey()) == null) {
							continue;
						}
						kafkaValues.put(eachField.getKey(), famsValues.get(eachField.getKey()));
					}
				}
			} catch (Exception e) {
				logger.error("set KafkaListener PostPocessor fail, exception:{}", e);
			}

		}
		return bean;
	}

}
