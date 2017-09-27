package com.hl.entity;

import java.io.Serializable;
import java.util.List;

public class Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Long age;
	private String desc;
	private List<String> hobbies;
	
	public Entity(String name, Long age, String desc) {
		super();
		this.name = name;
		this.age = age;
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public Entity(String name, Long age, String desc, List<String> hobbies) {
		super();
		this.name = name;
		this.age = age;
		this.desc = desc;
		this.hobbies = hobbies;
	}
	
	
	@Override
	public String toString() {
		return "Entity [name=" + name + ", age=" + age + ", desc=" + desc + "]";
	}
	public String toJsonString() {
		return "{\"name\":"+this.name+",\"age\":"+this.age+",\"desc\":"+this.desc+"}";
	}
	

}
