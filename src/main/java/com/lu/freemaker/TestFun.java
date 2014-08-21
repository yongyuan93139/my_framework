package com.lu.freemaker;

import java.io.IOException;
import java.util.Map;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class TestFun implements TemplateDirectiveModel {    
    
//    @Resource(name = "userService")    
//    private UserService userService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException
	{
		//userService.printHelloWorld(params);
		System.out.println("hello" + params);
		env.setVariable("testFun1",ObjectWrapper.DEFAULT_WRAPPER.wrap("==========hello" + params) );  
		if (body != null) {  
            body.render(env.getOut());  
        }
	}    
        
       
}    