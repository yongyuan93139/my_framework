package com.lu.service;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
	public void printHelloWorld(Map params){
		System.out.println("Hello World," + params);
	}
}
