package com.tinyurl.model;

import java.util.*;

public class URLEntry {
	private String shortUrl;
	private String baseUrl;
	private long index;
	private Date createTime;
	private Date lastAccessTime;
	private int numberOfAccess;
	
	public URLEntry(String shortUrl, String baseUrl, long index) {
		super();
		this.shortUrl = shortUrl;
		this.baseUrl = baseUrl;
		this.index = index;
		this.createTime = new Date();
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public int getNumberOfAccess() {
		return numberOfAccess;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void touch() {
		++numberOfAccess;
		lastAccessTime = new Date();
	}
}
