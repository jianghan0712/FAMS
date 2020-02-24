/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.communication;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;

/**
 * @Classname: FAMSEventListener
 * @Description: 消费者注解，封装了@KafkaListener，通过 @see FAMSListenerBeanPostPocessor
 *               进行拦截设置
 * @author jiang
 * @date 2020-02-11 14:37:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD,
		java.lang.annotation.ElementType.ANNOTATION_TYPE })
@Documented
@Repeatable(FAMSEventListeners.class)
@KafkaListener
public @interface FAMSEventListener {

	/**
	 * The unique identifier of the container managing for this endpoint.
	 * <p>
	 * If none is specified an auto-generated one is provided.
	 * <p>
	 * Note: When provided, this value will override the group id property in the
	 * consumer factory configuration, unless {@link #idIsGroup()} is set to false.
	 * <p>
	 * SpEL {@code #{...}} and property place holders {@code ${...}} are supported.
	 * 
	 * @return the {@code id} for the container managing for this endpoint.
	 * @see org.springframework.kafka.config.KafkaListenerEndpointRegistry#getListenerContainer(String)
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "id")
	String id() default "";

	/**
	 * The bean name of the
	 * {@link org.springframework.kafka.config.KafkaListenerContainerFactory} to use
	 * to create the message listener container responsible to serve this endpoint.
	 * <p>
	 * If not specified, the default container factory is used, if any.
	 * 
	 * @return the container factory bean name.
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "containerFactory")
	String containerFactory() default "";

	/**
	 * The topics for this listener. The entries can be 'topic name',
	 * 'property-placeholder keys' or 'expressions'. An expression must be resolved
	 * to the topic name. This uses group management and Kafka will assign
	 * partitions to group members.
	 * <p>
	 * Mutually exclusive with {@link #topicPattern()} and
	 * {@link #topicPartitions()}.
	 * 
	 * @return the topic names or expressions (SpEL) to listen to.
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "topics")
	String[] topics() default {};

	/**
	 * The topic pattern for this listener. The entries can be 'topic pattern', a
	 * 'property-placeholder key' or an 'expression'. The framework will create a
	 * container that subscribes to all topics matching the specified pattern to get
	 * dynamically assigned partitions. The pattern matching will be performed
	 * periodically against topics existing at the time of check. An expression must
	 * be resolved to the topic pattern (String or Pattern result types are
	 * supported). This uses group management and Kafka will assign partitions to
	 * group members.
	 * <p>
	 * Mutually exclusive with {@link #topics()} and {@link #topicPartitions()}.
	 * 
	 * @return the topic pattern or expression (SpEL).
	 * @see org.apache.kafka.clients.CommonClientConfigs#METADATA_MAX_AGE_CONFIG
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "topicPattern")
	String topicPattern() default "";

	/**
	 * The topicPartitions for this listener when using manual topic/partition
	 * assignment.
	 * <p>
	 * Mutually exclusive with {@link #topicPattern()} and {@link #topics()}.
	 * 
	 * @return the topic names or expressions (SpEL) to listen to.
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "topicPartitions")
	TopicPartition[] topicPartitions() default {};

	/**
	 * If provided, the listener container for this listener will be added to a bean
	 * with this value as its name, of type
	 * {@code Collection<MessageListenerContainer>}. This allows, for example,
	 * iteration over the collection to start/stop a subset of containers.
	 * <p>
	 * SpEL {@code #{...}} and property place holders {@code ${...}} are supported.
	 * 
	 * @return the bean name for the group.
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "containerGroup")
	String containerGroup() default "";

	/**
	 * Set an {@link org.springframework.kafka.listener.KafkaListenerErrorHandler}
	 * bean name to invoke if the listener method throws an exception.
	 * 
	 * @return the error handler.
	 * @since 1.3
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "errorHandler")
	String errorHandler() default "";

	/**
	 * Override the {@code group.id} property for the consumer factory with this
	 * value for this listener only.
	 * <p>
	 * SpEL {@code #{...}} and property place holders {@code ${...}} are supported.
	 * 
	 * @return the group id.
	 * @since 1.3
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "groupId")
	String groupId() default "";

	/**
	 * When {@link #groupId() groupId} is not provided, use the {@link #id() id} (if
	 * provided) as the {@code group.id} property for the consumer. Set to false, to
	 * use the {@code group.id} from the consumer factory.
	 * 
	 * @return false to disable.
	 * @since 1.3
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "idIsGroup")
	boolean idIsGroup() default true;

	/**
	 * When provided, overrides the client id property in the consumer factory
	 * configuration. A suffix ('-n') is added for each container instance to ensure
	 * uniqueness when concurrency is used.
	 * <p>
	 * SpEL {@code #{...}} and property place holders {@code ${...}} are supported.
	 * 
	 * @return the client id prefix.
	 * @since 2.1.1
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "clientIdPrefix")
	String clientIdPrefix() default "";

	/**
	 * A pseudo bean name used in SpEL expressions within this annotation to
	 * reference the current bean within which this listener is defined. This allows
	 * access to properties and methods within the enclosing bean. Default
	 * '__listener'.
	 * <p>
	 * Example: {@code topics = "#{__listener.topicList}"}.
	 * 
	 * @return the pseudo bean name.
	 * @since 2.1.2
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "beanRef")
	String beanRef() default "__listener";

	/**
	 * Override the container factory's {@code concurrency} setting for this
	 * listener. May be a property placeholder or SpEL expression that evaluates to
	 * a {@link Number}, in which case {@link Number#intValue()} is used to obtain
	 * the value.
	 * <p>
	 * SpEL {@code #{...}} and property place holders {@code ${...}} are supported.
	 * 
	 * @return the concurrency.
	 * @since 2.2
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "concurrency")
	String concurrency() default "";

	/**
	 * Set to true or false, to override the default setting in the container
	 * factory. May be a property placeholder or SpEL expression that evaluates to a
	 * {@link Boolean} or a {@link String}, in which case the
	 * {@link Boolean#parseBoolean(String)} is used to obtain the value.
	 * <p>
	 * SpEL {@code #{...}} and property place holders {@code ${...}} are supported.
	 * 
	 * @return true to auto start, false to not auto start.
	 * @since 2.2
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "autoStartup")
	String autoStartup() default "";

	/**
	 * Kafka consumer properties; they will supersede any properties with the same
	 * name defined in the consumer factory (if the consumer factory supports
	 * property overrides).
	 * <h3>Supported Syntax</h3>
	 * <p>
	 * The supported syntax for key-value pairs is the same as the syntax defined
	 * for entries in a Java {@linkplain java.util.Properties#load(java.io.Reader)
	 * properties file}:
	 * <ul>
	 * <li>{@code key=value}</li>
	 * <li>{@code key:value}</li>
	 * <li>{@code key value}</li>
	 * </ul>
	 * {@code group.id} and {@code client.id} are ignored.
	 * 
	 * @return the properties.
	 * @since 2.2.4
	 * @see org.apache.kafka.clients.consumer.ConsumerConfig
	 * @see #groupId()
	 * @see #clientIdPrefix()
	 */
	@AliasFor(annotation = KafkaListener.class, attribute = "properties")
	String[] properties() default {};

}
