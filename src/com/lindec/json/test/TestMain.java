package com.lindec.json.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

/**
 * @author lindec
 *
 */
public class TestMain {

	/**
	 * 测试三组数据：10000次 ，50000次，100000次 
	 * 
	 * 10000次： 
	 * 装配10000组数据所用时间（秒）：0.2
	 * Jackson所用时间（秒）：0.796 
	 * fastjson所用时间（秒）：0.3
	 * 
	 * 50000次
	 * 装配50000组数据所用时间（秒）：0.519 
	 * Jackson所用时间（秒）：1.678
	 * fastjson所用时间（秒）：0.858
	 * 
	 * 10000次 
	 * 装配100000组数据所用时间（秒）：0.935 
	 * Jackson所用时间（秒）：2.659
	 * fastjson所用时间（秒）：1.587
	 * 
	 */
	@Test
	public void test() {
		Utils.timeBegin();
		List<Corp> corps = new ArrayList<Corp>();
		int amount = 100000;
		for (int i = 0; i < amount; i++) {
			corps.add(Utils.fullObject(Corp.class));
		}
		Utils.timeEnd("装配" + amount + "组数据");

		// jsonson
		Utils.timeBegin();
		for (Corp corp : corps) {
			JSONObject jsonObject = JSONObject.fromObject(corp);

		}
		Utils.timeEnd("Jackson");

		// fastjson
		Utils.timeBegin();
		for (Corp corp : corps) {
			String string = JSON.toJSONString(corp);
		}
		Utils.timeEnd("fastjson");

	}
	
	
	/**
	 * fastjson写法简单，jackson写法繁琐
	 */
	@Test
	public void test2() {
		String Car1 = "{\"brand\":\"BMW\",\"value\":\"500000\"}";
		String Car2 = "{\"brand\":\"AUDI\",\"value\":\"300000\"}";
		
		 JSONObject jsonObject = JSONObject.fromObject(Car1);	
		 Car car1 = (Car)JSONObject.toBean(jsonObject,Car.class);
		 
		 System.out.println(car1.getBrand());
		 
		 Car car2 = JSON.parseObject(Car2, Car.class);
		 System.out.println(car2.getBrand());
		
	}
	
	
	/**
	 * 如果解析的JavaBean内有list集合，
	 * jackson需多添加一个关联的map，而fastjson会自动关联相关子集合
	 */
	@Test
	public void test3() {
		
		String Person1 = "{\"name\":\"lindec\",\"age\":26,\"cars\":[{\"brand\":\"BMW\",\"value\":\"500000\"},{\"brand\":\"AUDI\",\"value\":\"300000\"}]}";
		String Person2 = "{\"name\":\"miki\",\"age\":27,\"cars\":[{\"brand\":\"FORD\",\"value\":\"200000\"},{\"brand\":\"BYD\",\"value\":\"150000\"}]}";
		
		
		
		 JSONObject jsonObject = JSONObject.fromObject(Person1);
		 Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
         //persons 名称需对应各个list变量名
          classMap.put("cars", Car.class);
          Person  person1 = (Person ) JSONObject.toBean(jsonObject,Person.class,classMap);
		
		 System.out.println(person1.getCars().get(0).getBrand());
		 
		 
          Person person2 = JSON.parseObject(Person2, Person.class);
          System.out.println(person2.getCars().get(0).getBrand());
		 
		
		
	}

}
