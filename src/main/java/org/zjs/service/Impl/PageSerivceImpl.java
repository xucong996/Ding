package org.zjs.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zjs.dao.MongoDao;
import org.zjs.pojo.Page;
import org.zjs.service.PageService;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

@Service
public class PageSerivceImpl implements PageService {
	
	@Autowired
	MongoDao mongodao;
	
	@Override
	public List<Page> findAll() {
		// TODO Auto-generated method stub
		MongoCollection<Page> collection = mongodao.getPageCollection();
		FindIterable<Page> result = collection.find();
		
		List<Page> list = new ArrayList<Page>();
		
		Block<Page> block = new Block<Page>() {

			@Override
			public void apply(Page t) {
				// TODO Auto-generated method stub
				list.add(t);
			}
		};
		
		result.forEach(block);
		
		
		return list;
	}

	@Override
	public Page findOneByPageName(String name) {
		// TODO Auto-generated method stub
		MongoCollection<Page> collection = mongodao.getPageCollection();
		Page page = collection.find(Filters.eq("page", name)).first();
		return page;
	}

	@Override
	public String insertOne(String page, String url) {
		// TODO Auto-generated method stub
		MongoCollection<Page> collection = mongodao.getPageCollection();
		Page p = new Page();
		p.setPage(page);
		p.setUrl(url);
		collection.insertOne(p);
		return "插入" + page + "成功";
	}

	@Override
	public String deleteOne(String page) {
		// TODO Auto-generated method stub
		MongoCollection<Page> collection = mongodao.getPageCollection();
		collection.deleteOne(Filters.eq("page", page));
		return "删除" + page + "成功";
	}

}
