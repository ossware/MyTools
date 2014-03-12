package com.iunet.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	private static int count = 0;

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("hello,world!" + arg0.getFireInstanceId() + "--" + arg0.getFireTime() + "---" + arg0.getNextFireTime() + "---" + count++);
	}

}
