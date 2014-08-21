package com.lu.groovy;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;

public class PersonTest
{	

	@SuppressWarnings("deprecation")
	@Test
	public void test()
	{
		Person person = new Person();
		person.setAge(25);
		person.setName("小花");
		
		assertEquals(person.getName(), "小花");
	}
	
	

}
