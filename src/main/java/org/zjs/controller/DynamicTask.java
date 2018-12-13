package org.zjs.controller;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.zjs.bean.ImageBean;
import org.zjs.exception.PhantomjsTimeoutException;
import org.zjs.pojo.Task;
import org.zjs.util.ChatbotSend;
import org.zjs.util.PhantomjsDemo;
import org.zjs.util.TimingSend;

@Configuration
@EnableScheduling
public class DynamicTask {
	
	@Autowired
	private static ThreadPoolTaskScheduler threadPoolTaskScheduler = threadPoolTaskScheduler();
	
	private static Map<Task, ScheduledFuture<?>> futureMap = new Hashtable<Task, ScheduledFuture<?>>();
	
	private static Logger logger = LoggerFactory.getLogger(DynamicTask.class);
	
	@Bean
	public static ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
		poolTaskScheduler.setPoolSize(40);
		poolTaskScheduler.initialize();
		return poolTaskScheduler;
	}
	
	
	public static String startTest(ImageBean imageBean, String phantomjs_path, String phantomjs_js_path, String tt, Task task,List<String> robeIdList) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					imageBean.updateFileRealName();
					PhantomjsDemo pd = new PhantomjsDemo(imageBean.getRequestURL(), imageBean.getFileRealPath(), phantomjs_path, phantomjs_js_path);
					
					try {
						pd.generateImage();
					} catch (PhantomjsTimeoutException e) {
						// TODO Auto-generated catch block
						logger.info("生成图片时间超时，换一个试试吧");
						return ;
					}
					
					for(String robeId : robeIdList) {
						ChatbotSend.send(imageBean.getFileURL(),robeId);	
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		};
		logger.info("corn表达式为：" + tt);
		ScheduledFuture<?> future=threadPoolTaskScheduler.schedule(runnable,new CronTrigger(tt) );
		logger.info("目前状态为 ： " + future.isCancelled());
		futureMap.put(task, future);
		return "ok";
	}
	
	public static String endTask(List<Task> taskList) {
		int count = 0;
		for(Task task : taskList) {
			ScheduledFuture<?> future = futureMap.get(task);
			try {
				future.cancel(true);
			}catch(NullPointerException e) {
				LoggerFactory.getLogger(TimingSend.class).info(task.toString()+"这个任务运行状态中！");
			}
			futureMap.remove(task);
			count++ ;
		}
		LoggerFactory.getLogger(TimingSend.class).info("执行选择关闭，一共传入"+count+"个任务");
		System.gc();
		
		return "close";
	}

	public static Map<Task, ScheduledFuture<?>> getFutureMap() {
		return futureMap;
	}

	public static void setFutureMap(Map<Task, ScheduledFuture<?>> futureMap) {
		DynamicTask.futureMap = futureMap;
	}
	
	
	
	
	
}
