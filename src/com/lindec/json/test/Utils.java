package com.lindec.json.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

public class Utils {

	private static ThreadLocal<Long> begin = new ThreadLocal<Long>();

	/**
	 * 计时开始
	 */
	public static void timeBegin() {
		begin.set(System.currentTimeMillis());
	}

	/**
	 * 计时结束
	 * @param name 
	 */
	public static void timeEnd(String name) {
		double time = (System.currentTimeMillis() - begin.get()) / 1000.0;
		System.out.println(name + "所用时间（秒）：" + time);
		
	}
	
	/**
	 * 填充类属性
	 * @param cl
	 * @return
	 */
	public static <T> T fullObject(Class<T> cl) {
		T t = null;
		try {
			t = cl.newInstance();
			Method methods[] = cl.getMethods();
			for (Method method : methods) {
				// 如果是set方法,进行随机数据的填充
				if (method.getName().indexOf("set") == 0) {
					Class param = method.getParameterTypes()[0];
					if (param.equals(String.class)) {
						method.invoke(t, "holleworld");
					} else if (param.equals(Short.class)) {
						method.invoke(t, (short) new Random().nextInt(5));
					} else if (param.equals(Float.class)) {
						method.invoke(t, new Random().nextFloat());
					} else if (param.equals(Double.class)) {
						method.invoke(t, new Random().nextDouble());
					} else if (param.equals(Integer.class)) {
						method.invoke(t, new Random().nextInt(10));
					} else if (param.equals(Long.class)) {
						method.invoke(t, new Random().nextLong());
					} else if (param.equals(Date.class)) {
						method.invoke(t, new Date());
					} else if (param.equals(java.sql.Date.class)) {
						method.invoke(t,
								new java.sql.Date(System.currentTimeMillis()));
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return t;
	}
}
