package org.zjs.dao;



import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zjs.pojo.CpRobe;
import org.zjs.pojo.GroupSending;
import org.zjs.pojo.Page;
import org.zjs.pojo.Robe;
import org.zjs.pojo.Task;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Repository
public class MongoDao {
	
	@Autowired
	private DB db;
	
	@Autowired
	private MongoDatabase database;

	//robe Collection
	public MongoCollection<Robe> getRobeCollection() {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoCollection<Robe> collection = database.getCollection("robe", Robe.class);
		
		return collection.withCodecRegistry(pojoCodecRegistry);
	}
	
	//Company-Robe Collection
	public MongoCollection<CpRobe> getCompanyCollection() {
		
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoCollection<CpRobe> collection = database.getCollection("cprobe", CpRobe.class);
		
		return collection.withCodecRegistry(pojoCodecRegistry);
	}
	
	//page-URL Collection
	public MongoCollection<Page> getPageCollection() {
		
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoCollection<Page> collection = database.getCollection("page", Page.class);
		return collection.withCodecRegistry(pojoCodecRegistry);
	}
	
	public MongoCollection<Task> getTaskCollection() {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoCollection<Task> collection = database.getCollection("task", Task.class);
		return collection.withCodecRegistry(pojoCodecRegistry);
	}
	
	public MongoCollection<GroupSending> getGSCollection(){
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		MongoCollection<GroupSending> collection = database.getCollection("groupsending", GroupSending.class);
		return collection.withCodecRegistry(pojoCodecRegistry);
	}
	
	public DBCollection getDBCollection(String collectionName) {
		
		return db.getCollection(collectionName);
	}
	
	
}
