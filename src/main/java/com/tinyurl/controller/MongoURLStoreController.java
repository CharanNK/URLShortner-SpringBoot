package com.tinyurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<MongoURLStore>> getAllUrls(){
		List<MongoURLStore> listOfUrls = mongoUrlStoreService.getAllUrls();
		if(listOfUrls.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(listOfUrls,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/find-short-url", method = RequestMethod.GET)
	public ResponseEntity<String> getShortUrl(@RequestParam(value = "baseUrl", required = true) String baseUrl){
		String shortUrl = mongoUrlStoreService.getShortUrl(baseUrl);
		
		if(shortUrl==null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(shortUrl,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/find-base-url", method = RequestMethod.GET)
	public ResponseEntity<String> getBaseUrl(@RequestParam(value = "shortUrl", required = true) String shortUrl){
		String baseUrl = mongoUrlStoreService.getBaseUrl(shortUrl);
		
		if(baseUrl == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(baseUrl,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add-new-url", method = RequestMethod.PUT)
	public ResponseEntity<MongoURLStore> createNewEntry(@RequestParam(value = "baseUrl",required = true) String baseUrl){
		MongoURLStore newEntry = mongoUrlStoreService.createUrlEntry(baseUrl);
		
		if(newEntry==null){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else
			return new ResponseEntity<>(newEntry,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete-by-base-url", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteByBaseUrl(@RequestParam(value = "baseUrl",required = true) String baseUrl){
		boolean isEntryDeleted = mongoUrlStoreService.deleteUrlEntry(baseUrl);
		
		if(isEntryDeleted)
			return new ResponseEntity<>("Url entry deleted!",HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/get-created-date", method = RequestMethod.GET)
	public ResponseEntity<Date> findDateByUrl(@RequestParam(value="url",required = true) String url){
		Date createdDate = mongoUrlStoreService.getCreateDate(url);
		
		if(createdDate==null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Date>(createdDate,HttpStatus.OK);
	}
}
