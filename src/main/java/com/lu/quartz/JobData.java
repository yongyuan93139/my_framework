package com.lu.quartz;

import java.util.Date; 
public class JobData { 
    public String getData(){ 
       return "Data from JobData at "+new Date().toString(); 
    } 
} 
