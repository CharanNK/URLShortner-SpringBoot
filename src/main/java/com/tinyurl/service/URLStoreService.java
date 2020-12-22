package com.tinyurl.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tinyurl.model.URLEntry;
import com.tinyurl.model.URLStore;

@Service
public class URLStoreService {

	@Autowired
	private URLStore urlStore;

	public List<URLEntry> getAllURLs() {
		return urlStore.getAllUrls();
	}

	public String getShortURL(String baseURL) {
		URLEntry urlEntry = urlStore.getShortURL(baseURL);
		if (urlEntry != null)
			return urlEntry.getShortUrl();
		return "Short URL not created for this URL";
	}

	public String getBaseURL(String shortURL) {
		URLEntry urlEntry = urlStore.getBaseURL(shortURL);
		if (urlEntry != null)
			return urlEntry.getBaseUrl();
		return "This short URL does not exist in the system";
	}

	public URLEntry createShortURL(String baseURL) {
		return urlStore.saveNewURL(baseURL);
	}
	
	public String deleteURLEntry(String baseURL) {
		boolean isDeleted = urlStore.deleteEntry(baseURL);
		
		if(isDeleted)
			return "URL removed from the system.";
		else
			return "URL does not exist in the system";
	}

}
