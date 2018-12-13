package org.zjs.service;

import java.util.List;

import org.zjs.pojo.Page;

public interface PageService {
	
	List<Page> findAll();
	
	Page findOneByPageName(String name);

	String insertOne(String page, String url);

	String deleteOne(String page);
}
