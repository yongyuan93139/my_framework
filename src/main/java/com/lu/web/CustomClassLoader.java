package com.lu.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.apache.commons.io.FileUtils;
import com.lu.consts.WebConsts;
import com.lu.encrpt.RSAUtil;
import com.lu.util.LuUtil;

public class CustomClassLoader extends ClassLoader
{
	private static CustomClassLoader instance = null;
	
	public static CustomClassLoader getInstance(){
		if(instance == null){
			instance = new CustomClassLoader();
			return instance;
		}else{
			return instance;
		}
		
	}
	
	public Object biz(){
		byte[] bytes = LuUtil.getDecrptClass("com.lu.classloader.HelloWorld");
	    Class clazz = defineClass("com.lu.classloader.HelloWorld", bytes, 0, bytes.length);  
	    Method mainMethod;
		try
		{
			Object obj = clazz.newInstance();
			mainMethod = clazz.getMethod("sayHello");
			Object retObj = mainMethod.invoke(obj, null);
			return retObj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Object rsaBiz() throws Exception{
		String className = "com.lu.classloader.HelloWorld";
		File classFile = new File(WebConsts.classpath + className.replace(".", "/") + ".class");
		byte[] encrptCls = FileUtils.readFileToByteArray(classFile);
		
		String privateKeyStr = FileUtils.readFileToString(new File("d:/rsa_private_key.pem"));
		PrivateKey privateKey = RSAUtil.getPrivateKeyFromString(privateKeyStr);

		byte[] plainCls = RSAUtil.decrypt(encrptCls, privateKey);
		
		Class clazz = defineClass("com.lu.classloader.HelloWorld", plainCls, 0, plainCls.length);  
	    Method mainMethod;
		try
		{
			Object obj = clazz.newInstance();
			mainMethod = clazz.getMethod("sayHello");
			Object retObj = mainMethod.invoke(obj, null);
			return retObj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

