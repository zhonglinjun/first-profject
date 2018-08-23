package com.yr.bean;

public class P_User {
	private Integer id;
	private String name;
	private String sex;

	@Override
	public String toString() {
		return "P_User [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}