package com.tinyurl.demo.url;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLStoreController {
	
	@Autowired
	private URLStoreService urlStoreService;
	
	@RequestMapping({"/getallurls"})
	private List<URLStore> getAllURLs(){
		return urlStoreService.getAllURLs();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/urlservice/getshorturl")
	private String getShortURL(@RequestParam("baseURL") String baseURL) {
		
		System.out.println("inside getshorturl url received :"+baseURL);
		
		URLStore urlstore = urlStoreService.getShortURL(baseURL);
		
		if(urlstore != null)
			return urlstore.getShortUrl();
		
		return "No short URL";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/urlservice/getbaseurl")
	private String getBaseURL(@RequestParam("shorturl") String shortURL) {
		
		System.out.println("inside getshorturl url received :"+shortURL);
		
		URLStore urlstore = urlStoreService.getBaseURL(shortURL);
		
		if(urlstore != null)
			return urlstore.getOriginalUrl();
		
		return "No short URL";
	}
}
