package org.zjs.service;

import java.util.List;

import org.zjs.pojo.GroupSending;

import com.mongodb.MongoWriteException;

public interface GroupSendService {

	String registerOne(String company, String robeId, String URL ) throws MongoWriteException;
	
	List<GroupSending> findAll();
}
