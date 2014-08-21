package com.lu.webservice.cxf;

import java.io.Serializable;

public class UserInfo implements Serializable
{
	private static final long serialVersionUID = 1972213300130936297L;
	private String name;
	private int age;

	public UserInfo()
	{
	}

	public UserInfo(String name, int age)
	{
		super();
		this.name = name;
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

}
