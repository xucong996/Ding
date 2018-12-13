package org.zjs.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjs.dao.MongoDao;
import org.zjs.pojo.GroupSending;
import org.zjs.service.GroupSendService;

import com.mongodb.Block;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Service
public class GroupSendServiceImpl implements GroupSendService {
	
	@Autowired
	MongoDao mongoDao;
	
	@Override
	public String registerOne(String company, String robeId, String URL) throws MongoWriteException{
		// TODO Auto-generated method stub
		MongoCollection<GroupSending> collection = mongoDao.getGSCollection();
		collection.insertOne(new GroupSending(company, robeId, URL));
		
		return "加入向"+company+"发送";
	}

	@Override
	public List<GroupSending> findAll() {
		// TODO Auto-generated method stub
		MongoCollection<GroupSending> collection = mongoDao.getGSCollection();
		FindIterable<GroupSending> iterable = collection.find();
		
		ArrayList<GroupSending> list = new ArrayList<GroupSending>();
		
		Block<GroupSending> block = new Block<GroupSending>() {

			@Override
			public void apply(GroupSending t) {
				// TODO Auto-generated method stub
				list.add(t);
			}
		};
		iterable.forEach(block);
		return list;
	}

}
