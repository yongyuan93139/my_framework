package com.lu.freemaker;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class StringTemplateHelloWorld
{
	public static void main(String[] args)
	{
		
		String temp = "接口返回${testFun1()}";
		StringTemplateLoader strLoader = new StringTemplateLoader();
        strLoader.putTemplate("callLogTemplate", temp);
        Configuration config = new Configuration();
        config.setTemplateLoader(strLoader);
        
        Template template;
		try
		{
			template = config.getTemplate("callLogTemplate", "UTF-8");
			Map<String, Object> root = new HashMap<>();
	        root.put("test", "helloWorld");
	        root.put("testFun", new TestFun());
	        StringWriter out = new StringWriter();
	        template.process(root, out);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

        
        
	
	}
		
}
