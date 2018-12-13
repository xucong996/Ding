package org.zjs.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjs.dao.MongoDao;
import org.zjs.pojo.Task;
import org.zjs.service.TaskListService;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

@Service
public class TaskListServiceImpl implements TaskListService {
	
	@Autowired
	MongoDao mongoDao;
	@Override
	public String saveTask(List<Task> list) {
		// TODO Auto-generated method stub
		MongoCollection<Task> collection = mongoDao.getTaskCollection();
		collection.drop();
		if(list.size() != 0)
		collection.insertMany(list);
		return "success";
	}
	@Override
	public List<Task> getAllTask() {
		// TODO Auto-generated method stub
		MongoCollection<Task> collection = mongoDao.getTaskCollection();
		
		FindIterable<Task> find = collection.find();
		
		List<Task> list = new ArrayList<Task>();
		
		Block<Task> block = new Block<Task>() {

			@Override
			public void apply(Task t) {
				// TODO Auto-generated method stub
				list.add(t);
			}};
			
		find.forEach(block);
		return list;
	}
	@Override
	public String updateStatus() {
		// TODO Auto-generated method stub
		MongoCollection<Task> taskCollection = mongoDao.getTaskCollection();
		taskCollection.updateMany(Filters.eq("status", "运行"), Updates.set("status", "未运行"));
		return "启动初始化更新";
	}
	

}
