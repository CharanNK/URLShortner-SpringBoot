package com.tinyurl.demo.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloClass {
	
	@RequestMapping({"/hello","/hi"})
	public String sayHello() {
		return "Tiny URL service is running";
	}
}
