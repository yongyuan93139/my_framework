package com.lu.groovy

import org.junit.Test;
class GroovyPersonTest
{
	@Test
	void testToString(){
	  Person p=new Person("name":"ddd","age":18);
	  Assert.assertEquals("ddd-18", p.toString())
   }
}
