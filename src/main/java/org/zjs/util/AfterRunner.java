package org.zjs.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.zjs.service.TaskListService;

@Component
public class AfterRunner implements CommandLineRunner {
	
	@Autowired
	TaskListService taskListService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String updateStatus = taskListService.updateStatus();
		LoggerFactory.getLogger(AfterRunner.class).info("启动时初始化任务状态"+updateStatus);
	}

}
