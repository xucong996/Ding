package org.zjs.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjs.dao.MongoDao;
import org.zjs.pojo.CpRobe;
import org.zjs.service.CpRobeService;

import com.mongodb.Block;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Service
public class CpRobeServiceImpl implements CpRobeService {
	
	@Autowired
	MongoDao mongoDao;
	
	
	//得到CpRobe表中所有的数据
	@Override
	public List<CpRobe> findAll() {
		// TODO Auto-generated method stub
		
		MongoCollection<CpRobe> collection = mongoDao.getCompanyCollection();
		FindIterable<CpRobe> result = collection.find();
		
		List<CpRobe> list = new ArrayList<CpRobe>();
		
		Block<CpRobe> block = new Block<CpRobe>() {
			
			@Override
			public void apply(CpRobe t) {
				// TODO Auto-generated method stub
				list.add(t);
			}
		};
		
		result.forEach(block);
		
		return list;
	}
	
	//根据公司名字得到CpRobe表中数据
	@Override
	public CpRobe findOneByName(String companyName) {
		// TODO Auto-generated method stub
		MongoCollection<CpRobe> collection = mongoDao.getCompanyCollection();
		
		CpRobe cpRobe = collection.find(Filters.eq("company", companyName)).first();
		
		return cpRobe;
	}

	@Override
	public String insertOne(String company, String robeId) throws MongoWriteException{
		// TODO Auto-generated method stub
		MongoCollection<CpRobe> collection = mongoDao.getCompanyCollection();
		System.out.println(company +"    "+robeId);
		CpRobe cpRobe = new CpRobe(company, robeId);
		System.out.println(cpRobe);
		
		collection.insertOne(cpRobe);
		
		return "插入成功";
	}

	@Override
	public String deleteOne(String company) {
		// TODO Auto-generated method stub
		MongoCollection<CpRobe> collection = mongoDao.getCompanyCollection();
		collection.deleteOne(Filters.eq("company",company));
		return "注销" + company + "成功";
	}

}
