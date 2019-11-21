package com.railwayTicketManager.test.fileTest;

import java.io.Serializable;

public class DataTest implements Serializable{
	private int id;
	private String name;
	
	public DataTest(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public DataTest() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String gSetName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DataTest [id=" + id + ", name=" + name + "]";
	}
	
	
}
