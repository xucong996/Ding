package org.zjs.service.Impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjs.dao.MongoDao;
import org.zjs.pojo.Robe;
import org.zjs.service.RobeService;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Service
public class RobeServiceImpl implements RobeService{
	
	
	@Autowired
	MongoDao mongoDao;
	
	public void insertOne(Robe robe) {
		// TODO Auto-generated method stub
		MongoCollection<Robe> collection = mongoDao.getRobeCollection();
		collection.insertOne(robe);
	}

	@Override
	public Robe getOneByName(String robeName) {
		// TODO Auto-generated method stub
		System.out.println("执行getOneByName方法");
		MongoCollection<Robe> collection = mongoDao.getRobeCollection();
		Robe robe = collection.find(Filters.eq("robeName", robeName)).first();
		System.out.println("得到robe ： " + robe);
		return robe;
	}

}
