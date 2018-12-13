package org.zjs.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	
	
	@RequestMapping("/")
	public String Index() {
		LoggerFactory.getLogger(IndexController.class).info("进入IndexController");
		return "index.3.0";
	}
	
	
	@RequestMapping("/day")
	public String newIndex() {
		LoggerFactory.getLogger(IndexController.class).info("Sending every day");
		return "index.3.1";
	}
	
	@RequestMapping("/RunningTask")
	public String RunningTask() {
		return "RunningTask";
	}
	
	
}
