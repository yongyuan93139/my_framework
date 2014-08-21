package com.lu.groovy

class Person
{
	def name;
	def age;
	
	//Groovy return 可有可无， 默认返回方法的最后一行的值
	String toString()
	{
		"$name,$age" //
	}
	
	static void main(def args){
		def person = new Person();
		person.age = 12;
		person.name = "小花";
		println person;
		
		person = new Person(['age':15,'name':'小红'])
		println person;
		
		person ? person.cusPrint(person) : cusPrint();
	}
	
	static cusPrint(def person){
		println "person not null";
	}
	
	static cusPrint(){
		println "person null";
	}
}
