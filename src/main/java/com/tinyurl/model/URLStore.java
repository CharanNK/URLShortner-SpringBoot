package com.tinyurl.model;
import java.util.*;

import org.springframework.stereotype.Component;

import com.tinyurl.util.TinyurlUtil;

@Component
public class URLStore {
	private Map<String, URLEntry> shortUrlMap = new HashMap<>();
	private Map<String, URLEntry> baseUrlMap = new HashMap<>();
	private long nextIndex = 9837912415197L;
	
	public URLEntry saveNewURL(String baseURL) {
		URLEntry tinyURLEntry = baseUrlMap.get(baseURL);
		
		if(tinyURLEntry==null) {
			String shortURL = TinyurlUtil.generateShortURL(baseURL);
			tinyURLEntry = new URLEntry(shortURL, baseURL , nextIndex++);
			shortUrlMap.put(shortURL, tinyURLEntry);
			baseUrlMap.put(baseURL, tinyURLEntry);
			return tinyURLEntry;
		}
		
		return tinyURLEntry;
	}
	
	public URLEntry getBaseURL(String shortURL) {
		return shortUrlMap.get(shortURL);
	}
	
	public URLEntry getShortURL(String baseURL) {
		return baseUrlMap.get(baseURL);
	}
	
	public List<URLEntry> getAllUrls(){
		List<URLEntry> urls = new ArrayList<>();
		for(Map.Entry<String, URLEntry> baseURL : baseUrlMap.entrySet()) {
			urls.add(baseURL.getValue());
		}
		return urls;
	}
	
	public boolean deleteEntry(String baseURL) {
		if(!TinyurlUtil.isValidURL(baseURL))
			return false;
		
		URLEntry urlEntry = baseUrlMap.get(baseURL);
		if(urlEntry==null)
			return false;
		String tinyUrl = urlEntry.getShortUrl();
		
		baseUrlMap.remove(baseURL);
		shortUrlMap.remove(tinyUrl);
		
		return true;
	}
}
