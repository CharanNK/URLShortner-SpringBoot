package com.tinyurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tinyurl.model.MongoURLStore;
import com.tinyurl.util.TinyurlUtil;

import java.util.*;

@Service
public class MongoURLStoreService {
	
	private long index = 9837347L;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<MongoURLStore> getAllUrls(){
		Query query = new Query().with(Sort.by(Sort.Direction.ASC,"index"));
		return mongoTemplate.find(query, MongoURLStore.class);
	}
	
	public String getBaseUrl(String shortUrl) {
		
		if(!TinyurlUtil.isValidURL(shortUrl))
			return null;
		
		Query query = new Query().addCriteria(Criteria.where("shortUrl").is(shortUrl));
		MongoURLStore urlEntry = mongoTemplate.findOne(query, MongoURLStore.class);
		
		return urlEntry.getBaseUrl();
	}
	
	public String getShortUrl(String baseUrl) {
		
		if(!TinyurlUtil.isValidURL(baseUrl))
			return null;
		
		Query query = new Query().addCriteria(Criteria.where("baseUrl").is(baseUrl));
		MongoURLStore urlEntry = mongoTemplate.findOne(query, MongoURLStore.class);
		
		return urlEntry.getShortUrl();
	}
	
	public MongoURLStore createUrlEntry(String baseUrl) {
		if(!TinyurlUtil.isValidURL(baseUrl))
			return null;
		
		Query query = new Query().addCriteria(Criteria.where("baseUrl").is(baseUrl));
		MongoURLStore urlEntry = mongoTemplate.findOne(query, MongoURLStore.class);
		
		if(urlEntry!=null)
			return urlEntry;
		else
			urlEntry = new MongoURLStore(++index, baseUrl, TinyurlUtil.generateShortURL(baseUrl));
		
		mongoTemplate.save(urlEntry);
		
		return urlEntry;
	}
	
	public boolean deleteUrlEntry(String baseUrl) {
		if(!TinyurlUtil.isValidURL(baseUrl))
			return false;
		
		Query query = new Query().addCriteria(Criteria.where("baseUrl").is(baseUrl));
		MongoURLStore urlEntry = mongoTemplate.findOne(query, MongoURLStore.class);
		
		if(urlEntry!=null) {
			mongoTemplate.remove(urlEntry);
			return true;
		}else {
			return false;
		}
	}
}
