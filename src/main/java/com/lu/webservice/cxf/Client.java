package com.lu.webservice.cxf;

import java.util.ArrayList;
import java.util.List;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client
{
	public static void main(String[] args)
	{
		invokeBySpring();
		invoke();
	}

	/**
	 * 通过Spring测试webservices
	 */
	public static void invokeBySpring()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-cxf-client.xml");
		IHelloServices helloServices = context.getBean("helloClient", IHelloServices.class);

		List<UserInfo> users = new ArrayList<UserInfo>();
		users.add(new UserInfo("vicky", 23));
		users.add(new UserInfo("caty", 23));
		users.add(new UserInfo("ivy", 23));
		users.add(new UserInfo("kelly", 23));
		String helloAll = helloServices.sayHelloToAll(users);

		String hello = helloServices.sayHello("luhp");

		System.out.println(helloAll);
		System.out.println(hello);
		System.out.println(helloServices.request("request"));
	}

	public static void invoke()
	{
		//创建WebService客户端代理工厂     
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		//注册WebService接口     
		factory.setServiceClass(IHelloServices.class);
		//设置WebService地址     
		factory.setAddress("http://localhost:8080/myframework/services/HelloServices");
		IHelloServices helloServices = (IHelloServices) factory.create();
		System.out.println("invoke helloServices webservice...");
		String hello = helloServices.sayHello("vicky");

		System.out.println(hello);
	}
}
