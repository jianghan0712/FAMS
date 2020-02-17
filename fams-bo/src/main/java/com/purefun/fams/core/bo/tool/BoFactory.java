/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.core.bo.tool;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.purefun.fams.common.util.UuidUtil;

/**
 * @Classname: BoFactory
 * @Description: bo的工厂类
 * @author jiang
 * @date 2020-02-06 14:40:47
 */
public class BoFactory {
	/**
	 * 创建bo需调用此方法
	 * 
	 * @MethodName: createBo
	 * @Description:
	 * @author jiang
	 * @date 2020-02-06 14:41:05
	 * @param c1 bo的模板类
	 * @return
	 */
	public static Object createBo(Class<?> c1) {
		fstpbo annotation = (fstpbo) c1.getAnnotation(fstpbo.class);
		if (annotation == null) {
			return null;
		}

		String[] spiltTemp = c1.getName().split("\\.");
		int len = spiltTemp.length;
		StringBuffer otwBoName = new StringBuffer();
		for (int i = 0; i < len - 1; i++)
			otwBoName.append(spiltTemp[i]).append(".");

		otwBoName.append("otw.").append(spiltTemp[len - 1]).append("_OTW");
		Object ret = null;

		try {
			Object bo = c1.newInstance();
			Field boid = c1.getField("boid");
			boid.set(bo, annotation.boid());
			Field des = c1.getField("destination");
			des.set(bo, annotation.destination());
			Field uuid = c1.getField("uuid");
			uuid.set(bo, UuidUtil.createUuid());

			Field[] fileds = c1.getFields();
			for (Field e : fileds) {
				if (e.getName().equalsIgnoreCase("boid") || e.getName().equalsIgnoreCase("destination")
						|| e.getName().equalsIgnoreCase("uuid"))
					continue;
				Class<?> type = e.getType();
				if (type.equals(java.lang.String.class)) {
					e.set(bo, "");
				} else if (type.equals(long.class)) {
					e.set(bo, -1L);
				} else if (type.equals(int.class)) {
					e.set(bo, -1);
				}
			}
			Class c2 = Class.forName(otwBoName.toString());
			ret = c2.getConstructor(c1).newInstance(bo);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException
				| SecurityException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}
}
