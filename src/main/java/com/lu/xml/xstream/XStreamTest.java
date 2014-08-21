package com.lu.xml.xstream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.thoughtworks.xstream.XStream;

public class XStreamTest
{
	public static void main(String[] args)
	{
		XStream xstream = new XStream();
		//自动检测annotation
		xstream.autodetectAnnotations(true);
		Person person = new Person();
		person.setName("pli");
		person.setAge(18);

		List<String> girls = new ArrayList<>();
		String girl1 = "yuandongdong";
		String girl2 = "zhanglili";
		girls.add(girl1);
		girls.add(girl2);
		person.setGirlFriends(new ArrayList<String>());

		person.setBirthday(new Date());
		person.setHobby(null);
		System.out.println(xstream.toXML(person));
	}
}
