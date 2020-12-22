package com.tinyurl.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.model.URLEntry;
import com.tinyurl.model.URLStore;
import com.tinyurl.service.URLStoreService;
import com.tinyurl.util.TinyurlUtil;

@RestController
public class URLStoreController {
	
	@Autowired
	private URLStoreService urlStoreService;
	
	@RequestMapping({"/getallurls"})
	private List<URLEntry> getAllURLs(){
		return urlStoreService.getAllURLs();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/urlservice/getshorturl")
	private String getShortURL(@RequestParam(value="baseurl",required=true) String baseURL) {
		
		if(!TinyurlUtil.isValidURL(baseURL))
			return "Invalid URL";
		
		return urlStoreService.getShortURL(baseURL);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/urlservice/getbaseurl")
	private String getBaseURL(@RequestParam(value="shorturl",required = true) String shortURL) {
		
		if(!TinyurlUtil.isValidURL(shortURL))
			return "Invalid URL";
		
		return urlStoreService.getBaseURL(shortURL);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "urlservice/addnewurl")
	private URLEntry createShortURL(@RequestParam(value="baseurl",required = true) String baseURL) {
		if(!TinyurlUtil.isValidURL(baseURL))
			return null;
		
		return urlStoreService.createShortURL(baseURL);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "urlservice/removeurl")
	private String removeURL(@RequestParam(value="baseurl",required=true) String baseURL) {
		return urlStoreService.deleteURLEntry(baseURL);
	}
}
