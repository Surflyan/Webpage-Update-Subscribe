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
		//Scheduler: 代表一个quartz的独立容器，Trigger 和 JobDetail可以在注册到Scheduler中
		//通过SchedulerFactory 可以创建一个Scheduler实例
	    SchedulerFactory sf = new StdSchedulerFactory();
	    sched = sf.getScheduler();


	    //创建Job实例
		JobDetail crawlJob = JobBuilder
				.newJob(CrawlJob.class)
				.withIdentity("crawlJob", "group1").build();
		JobDetail timingPush= JobBuilder
				.newJob(PushJob.class)
				.withIdentity("timingPush", "group1").build();

	    //定义Job执行的触发规则
		//这里采用CronTrigger子类，通过Cron定义出复杂的时间规则
	    CronTrigger trigger1 = TriggerBuilder
            .newTrigger()
            .withIdentity("cronTrigger","group")
            .withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0 0/1 * * ?")) //每小时执行一次
            .build();
        CronTrigger trigger2 = TriggerBuilder
            .newTrigger()
            .withIdentity("cronTrigger2","group")
            .withSchedule(
                    CronScheduleBuilder.cronSchedule("0 0 20 * * ?")) //每天 20:00 执行一次
            .build();

        //将Trigger绑定到一个JobDetail中，Trigger触发，JobDetail执行
	    sched.scheduleJob(crawlJob, trigger1);
	    sched.scheduleJob(timingPush, trigger2);

        sched.start();
	}

	public static void shutdown() throws SchedulerException {
		if(sched.isStarted()) sched.shutdown();
	}
}
