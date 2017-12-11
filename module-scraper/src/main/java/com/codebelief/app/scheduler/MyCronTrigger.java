package com.codebelief.app.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/*
 * @author: Surflyan<yanjilaing0128@outlook.com>
 * Date: 2017-10-18
 */

public class MyCronTrigger {
	private static Scheduler sched;

	public static void start() throws Exception {
		//Scheduler: ����һ��quartz�Ķ���������Trigger �� JobDetail������ע�ᵽScheduler��
		//ͨ��SchedulerFactory ���Դ���һ��Schedulerʵ��
	    SchedulerFactory sf = new StdSchedulerFactory();
	    sched = sf.getScheduler();


	    //����Jobʵ��
		JobDetail crawlJob = JobBuilder
				.newJob(CrawlJob.class)
				.withIdentity("crawlJob", "group1").build();
		JobDetail timingPush= JobBuilder
				.newJob(PushJob.class)
				.withIdentity("timingPush", "group1").build();

	    //����Jobִ�еĴ�������
		//�������CronTrigger���࣬ͨ��Cron��������ӵ�ʱ�����
	    CronTrigger trigger1 = TriggerBuilder
            .newTrigger()
            .withIdentity("cronTrigger","group")
            .withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0 0/1 * * ?")) //ÿСʱִ��һ��
            .build();
        CronTrigger trigger2 = TriggerBuilder
            .newTrigger()
            .withIdentity("cronTrigger2","group")
            .withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0 20 * * ?")) //ÿ�� 20:00 ִ��һ��
            .build();

        //��Trigger�󶨵�һ��JobDetail�У�Trigger������JobDetailִ��
	    sched.scheduleJob(crawlJob, trigger1);
	    sched.scheduleJob(timingPush, trigger2);

        sched.start();
	}

	public static void shutdown() throws SchedulerException {
		if(sched.isStarted()) sched.shutdown();
	}
}
