package org.zjs.service;

import java.util.List;

import org.zjs.pojo.CpRobe;

import com.mongodb.MongoWriteException;

public interface CpRobeService {
	List<CpRobe> findAll();
	
	CpRobe findOneByName(String CompanyName);

	String insertOne(String company, String robeId) throws MongoWriteException;

	String deleteOne(String company);
}
