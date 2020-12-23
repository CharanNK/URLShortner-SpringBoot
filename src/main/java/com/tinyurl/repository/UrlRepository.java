package com.tinyurl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tinyurl.model.MongoURLStore;

public interface UrlRepository extends MongoRepository<MongoURLStore, Long> {

}
