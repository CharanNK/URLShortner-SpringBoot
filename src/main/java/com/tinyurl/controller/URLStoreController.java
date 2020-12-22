package com.tinyurl.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tinyurl.model.URLStore;
import com.tinyurl.service.URLStoreService;
import com.tinyurl.util.TinyurlUtil;

@RestController
public class URLStoreController {
	
	@Autowired
	private URLStoreService urlStoreService;
	
	@RequestMapping({"/getallurls"})
	private List<URLStore> getAllURLs(){
		return urlStoreService.getAllURLs();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/urlservice/getshorturl")
	private String getShortURL(@RequestParam(value="baseurl",required=true) String baseURL) {
		
		if(!TinyurlUtil.isValidURL(baseURL))
			return "Invalid URL";
		
		URLStore urlstore = urlStoreService.getShortURL(baseURL);
		
		if(urlstore != null)
			return urlstore.getShortUrl();
		
		return "No short URL";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/urlservice/getbaseurl")
	private String getBaseURL(@RequestParam(value="shorturl",required = true) String shortURL) {
		
		if(!TinyurlUtil.isValidURL(shortURL))
			return "Invalid URL";
		
		URLStore urlstore = urlStoreService.getBaseURL(shortURL);
		
		if(urlstore != null)
			return urlstore.getOriginalUrl();
		
		return "No short URL";
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "urlservice/addnewurl")
	private URLStore createShortURL(@RequestParam(value="baseurl",required = true) String baseURL) {
		if(!TinyurlUtil.isValidURL(baseURL))
			return null;
		
		return urlStoreService.createShortURL(baseURL);
	}
}
