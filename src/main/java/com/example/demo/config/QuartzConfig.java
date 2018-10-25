package com.example.demo.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.task.MyTask;

/**
 * Quartz定时任务配置
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Configuration
public class QuartzConfig {
	
//	@Bean
//	public JobDetail myTaskDetail() {
//		return JobBuilder.newJob(MyTask.class).withIdentity("myTask2").storeDurably().build();
//	}
//
//	@Bean
//	public Trigger uploadTaskTrigger() {
//		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
//		return TriggerBuilder.newTrigger().forJob(myTaskDetail()).withIdentity("myTask2")
//				.withSchedule(scheduleBuilder).build();
//	}
	
}
