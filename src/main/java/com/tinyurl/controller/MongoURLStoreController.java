package com.tinyurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.model.MongoURLStore;
import com.tinyurl.service.MongoURLStoreService;
import java.util.*;

@RestController
@RequestMapping("/mongoservice")
public class MongoURLStoreController {
	
	@Autowired
	MongoURLStoreService mongoUrlStoreService;
	
	@RequestMapping(value = "/get-all-urls", method = RequestMethod.GET)
	public List<MongoURLStore> getAllUrls(){
		return mongoUrlStoreService.getAllUrls();
	}
}
