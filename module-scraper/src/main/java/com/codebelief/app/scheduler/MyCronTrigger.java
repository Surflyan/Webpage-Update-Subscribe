package com.codebelief.app.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/*
 * @author: Surflyan<yanjilaing0128@outlook.com>
 * Date: 2017-10-18
 */

public class MyCronTrigger {
	
	public static void main(String[] args) throws Exception {
		
		//Scheduler: ����һ��quartz�Ķ���������Trigger �� JobDetail������ע�ᵽScheduler��
		//ͨ��SchedulerFactory ���Դ���һ��Schedulerʵ��
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    
	    
	    //����Jobʵ��
		JobDetail job = JobBuilder
				.newJob(CrawlJob.class)
				.withIdentity("crawJob", "group1").build();
	
	    //����Jobִ�еĴ�������
		//�������CronTrigger���࣬ͨ��Cron��������ӵ�ʱ�����
	    CronTrigger trigger = TriggerBuilder
			    .newTrigger()
			    .withIdentity("cronTrigger","group")
			    .withSchedule(
					    CronScheduleBuilder.cronSchedule("0/50 * * * * ?"))
			    .build();
        sched.start();
        
        //��Trigger�󶨵�һ��JobDetail�У�Trigger������JobDetailִ��
	    sched.scheduleJob(job,trigger);
	}
}
