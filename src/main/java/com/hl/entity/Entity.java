package com.hl.entity;

import java.util.List;

public class Entity {
	private int age;
	private String desc;
	private List<String> hobbies;
	private String name;
	public Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Entity(String name, int age, String desc, List<String> hobbies) {
		super();
		this.name = name;
		this.age = age;
		this.desc = desc;
		this.hobbies = hobbies;
	}
	public int getAge() {
		return age;
	}
	public String getDesc() {
		return desc;
	}
	public List<String> getHobbies() {
		return hobbies;
	}
	public String getName() {
		return name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Entity [name=" + name + ", age=" + age + ", desc=" + desc + "]";
	}
	

}
