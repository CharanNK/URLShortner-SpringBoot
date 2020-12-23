package com.tinyurl.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tinyurl.model.MongoURLStore;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlRepositoryTest {
	
	private long index = 9856790L;
	
	@Autowired
	UrlRepository urlRepository;

	@Test
	public void createRows() {
		urlRepository.deleteAll();
		urlRepository.save(new MongoURLStore(++index,"https://google.com", "https://tinyurldemo.com/as213oY"));
		urlRepository.save(new MongoURLStore(++index,"https://instagram.com", "https://tinyurldemo.com/23eyOp6"));
		urlRepository.findAll().forEach(System.out::println);
	}

}
