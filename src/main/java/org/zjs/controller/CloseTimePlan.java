package org.zjs.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zjs.pojo.Task;

@RestController
public class CloseTimePlan {
	private Logger loger =  LoggerFactory.getLogger(CloseTimePlan.class);
	
	@RequestMapping("/close")
	@ResponseBody
	public String toClose(@RequestBody List<Task> taskList) {
		System.out.println("进入close方法");
		loger.info("执行Close()之前有" + Thread.activeCount()+"个线程正在执行");
		loger.info("执行Close()之前有" + DynamicTask.getFutureMap().size()+"个任务正在执行");
		//TimingSend.closeAllTimer(taskList);
		DynamicTask.endTask(taskList);
		loger.info("执行Close()之后有" + DynamicTask.getFutureMap().size()+"个任务正在执行");
		//loger.info("执行Close()之后有" + TimingSend.getTimerMap().size()+"个任务正在执行");
		loger.info("执行Close()之后有" + Thread.activeCount()+"个线程正在执行");
		return "关闭成功";
	}
}
