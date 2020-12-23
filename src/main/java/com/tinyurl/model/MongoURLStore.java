package com.tinyurl.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "urlstore")
public class MongoURLStore {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long index;
	
	@Indexed(unique=true)
	private String baseUrl;
	
	@Indexed(unique = true)
	private String shortUrl;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date createTime;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date lastAccessTime;
	private int numberOfAccess;
	
	
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public int getNumberOfAccess() {
		return numberOfAccess;
	}
	public void setNumberOfAccess(int numberOfAccess) {
		this.numberOfAccess = numberOfAccess;
	}
	
	public MongoURLStore(long index,String baseUrl, String shortUrl) {
		super();
		this.baseUrl = baseUrl;
		this.shortUrl = shortUrl;
		this.index = index;
		createTime = new Date();
		lastAccessTime = createTime;
		++numberOfAccess;
	}
	
	@Override
	public String toString() {
		return "MongoURLStore [index=" + index + ", baseUrl=" + baseUrl + ", shortUrl=" + shortUrl + ", createTime="
				+ createTime + ", lastAccessTime=" + lastAccessTime + ", numberOfAccess=" + numberOfAccess + "]";
	}
	
}
