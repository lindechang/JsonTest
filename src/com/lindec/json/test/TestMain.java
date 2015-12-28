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
	 * �����������ݣ�10000�� ��50000�Σ�100000�� 
	 * 
	 * 10000�Σ� 
	 * װ��10000����������ʱ�䣨�룩��0.2
	 * Jackson����ʱ�䣨�룩��0.796 
	 * fastjson����ʱ�䣨�룩��0.3
	 * 
	 * 50000��
	 * װ��50000����������ʱ�䣨�룩��0.519 
	 * Jackson����ʱ�䣨�룩��1.678
	 * fastjson����ʱ�䣨�룩��0.858
	 * 
	 * 10000�� 
	 * װ��100000����������ʱ�䣨�룩��0.935 
	 * Jackson����ʱ�䣨�룩��2.659
	 * fastjson����ʱ�䣨�룩��1.587
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
		Utils.timeEnd("װ��" + amount + "������");

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
	 * fastjsonд���򵥣�jacksonд������
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
	 * ���������JavaBean����list���ϣ�
	 * jackson������һ��������map����fastjson���Զ���������Ӽ���
	 */
	@Test
	public void test3() {
		
		String Person1 = "{\"name\":\"lindec\",\"age\":26,\"cars\":[{\"brand\":\"BMW\",\"value\":\"500000\"},{\"brand\":\"AUDI\",\"value\":\"300000\"}]}";
		String Person2 = "{\"name\":\"miki\",\"age\":27,\"cars\":[{\"brand\":\"FORD\",\"value\":\"200000\"},{\"brand\":\"BYD\",\"value\":\"150000\"}]}";
		
		
		
		 JSONObject jsonObject = JSONObject.fromObject(Person1);
		 Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
         //persons �������Ӧ����list������
          classMap.put("cars", Car.class);
          Person  person1 = (Person ) JSONObject.toBean(jsonObject,Person.class,classMap);
		
		 System.out.println(person1.getCars().get(0).getBrand());
		 
		 
          Person person2 = JSON.parseObject(Person2, Person.class);
          System.out.println(person2.getCars().get(0).getBrand());
		 
		
		
	}

}
