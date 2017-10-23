package com.myapi.beans;

import javax.ws.rs.QueryParam;

public class Customer {

	@QueryParam("id")
	private int id;
	@QueryParam("name")
	private String name;
	@QueryParam("city")
	private String city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
