package org.zjs.service;

import org.zjs.pojo.Robe;

public interface RobeService {
	
	void insertOne(Robe robe) ;
	
	Robe getOneByName(String name);
}
