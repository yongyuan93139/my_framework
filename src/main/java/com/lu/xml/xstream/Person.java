package com.lu.xml.xstream;

import java.util.Date;
import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("person")
//xml节点的别名，默认是包名+类名
public class Person
{
	private String name;
	@XStreamAsAttribute
	//作为xml节点的属性
	private int age;
	//@XStreamImplicit(itemFieldName = "girl") //去掉girlFriends节点，直接用girl列表节点String
	//@XStreamOmitField
	//忽略属性
	private List<String> girlFriends;
	@XStreamConverter(value = DateConverter.class)
	private Date birthday;
	private String hobby;

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public List<String> getGirlFriends()
	{
		return girlFriends;
	}

	public void setGirlFriends(List<String> girlFriends)
	{
		this.girlFriends = girlFriends;
	}

	public Date getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	public String getHobby()
	{
		return hobby;
	}

	public void setHobby(String hobby)
	{
		this.hobby = hobby;
	}

}
