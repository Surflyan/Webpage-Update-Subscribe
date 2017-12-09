package com.codebelief.app.scheduler;

import com.codebelief.app.scraper.*;

import java.text.SimpleDateFormat;
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
		//System.out.println(context.getTrigger().getCalendarName() + "triggered.time is :" + (new Date()));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        System.out.println(sdf.format(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
