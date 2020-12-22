package com.tinyurl.model;
import java.util.*;

public class URLStore {
	private String originalUrl;
	private String shortUrl;
	private Date createTime;
	
	public URLStore() {
	}
	
	public URLStore(String originalUrl, String shortUrl) {
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	
	
	
}
