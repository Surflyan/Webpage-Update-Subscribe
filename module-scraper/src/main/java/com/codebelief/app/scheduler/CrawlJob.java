package com.codebelief.app.scheduler;


import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/*
 * @author: Surflyan<yanjilaing0128@outlook.com>
 * Date: 2017-10-18
 */

public class CrawlJob implements Job {
	//���� Job �ӿڷ���
	// JobExecutionContext ���ṩ�˵��������ĵĸ�����Ϣ
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(context.getTrigger().getCalendarName() + "triggered.time is :" + (new Date()));
		//System.out.println("hello quartz");
	}

}
