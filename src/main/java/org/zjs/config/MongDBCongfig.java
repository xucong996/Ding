package org.zjs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;

@Configuration
public class MongDBCongfig {
	
	@Bean
	public MongoClient getMongoClient() {
		//创建多个连接
		//MongoClient mongoClient = MongoClients.create("mongodb://hostOne:27017,hostTwo:27018");
		
		//指定url来new一个Clien对象
		//MongoClientURI connectionString = new MongoClientURI("mongodb://hostOne:27017,hostTwo:27017")
		return new MongoClient("10.10.6.82",27017);
		//return new MongoClient("localhost",27017);
		
	}
	
	@Bean
	public MongoDatabase getDatabase() {
		return getMongoClient().getDatabase("report");
	}
	
	@Deprecated
	@Bean
	public DB getMongoDB() {
		return getMongoClient().getDB("test");
	}
}
