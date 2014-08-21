package com.lu.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobDetailTask extends QuartzJobBean
{

	private JobData jobData;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException
	{
		System.out.println("JobDetailJob executed...");
	}

	public JobData getJobData()
	{
		return jobData;
	}

	public void setJobData(JobData jobData)
	{
		this.jobData = jobData;
	}
	
	
}
