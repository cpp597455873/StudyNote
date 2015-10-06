package com.cpp.sprinfmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/nihao")
@Controller
public class HelloWorld {

	@RequestMapping("/hello.do")
	public String hello() {
		System.out.println("hello");
		return "login";
	}
}
