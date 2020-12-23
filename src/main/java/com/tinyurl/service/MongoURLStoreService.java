package com.tinyurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tinyurl.model.MongoURLStore;

import java.util.*;

@Service
public class MongoURLStoreService {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<MongoURLStore> getAllUrls(){
		Query query = new Query().with(Sort.by(Sort.Direction.ASC,"index"));
		return mongoTemplate.find(query, MongoURLStore.class);
	}
	
	
}
