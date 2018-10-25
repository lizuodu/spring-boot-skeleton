package com.example.demo.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Java自带定时任务配置
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
@Configuration
@EnableScheduling
public class ScheduledConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		//ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        //taskRegistrar.setScheduler(executorService);	
	}
	
//	@Scheduled(cron = "* * * * * ?") //1s
//    public void myTask1(){
//        System.out.println("Scheduled任务开始");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Scheduled任务结束");
//    }

}
