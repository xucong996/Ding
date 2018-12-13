package org.zjs.service;

import java.util.List;


import org.zjs.pojo.Task;

public interface TaskListService {
	
	String saveTask(List<Task> list);
	
	List<Task> getAllTask();
	
	String updateStatus();
}
