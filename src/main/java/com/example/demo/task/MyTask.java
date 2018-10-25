package com.example.demo.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;


@DisallowConcurrentExecution
/**
 * Quartz任务执行逻辑
 * 
 * @author lizuodu
 * @date 2018/09/27
 */
public class MyTask extends QuartzJobBean {
	
	private final Logger LOGGER = LoggerFactory.getLogger(MyTask.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LOGGER.info("Quartz任务开始");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Quartz任务结束");		
	}

}
