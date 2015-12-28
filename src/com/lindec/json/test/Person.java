package com.lindec.json.test;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private String name;
	private int age;
	private List<Car> cars = new ArrayList<Car>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	

}
