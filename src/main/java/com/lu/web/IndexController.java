package com.lu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView index() {
    	ModelAndView mv = new ModelAndView("index");
    	CustomClassLoader bizClassLoarder = CustomClassLoader.getInstance();
//    	Object obj = bizClassLoarder.biz();
    	Object obj = null;
		try
		{
			obj = bizClassLoarder.rsaBiz();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	System.out.println(obj);
    	mv.addObject("ret", obj);
        return mv;
    }
}
