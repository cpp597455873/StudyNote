package com.cpp.sprinfmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/nihao")   //请求前缀 可选项
@Controller
public class HelloWorld {

	@RequestMapping("/hello.do") //请求前缀  若不在类上面配置RequestMapping就是相对跟路径的，若配置了，就像这样  /nihao/hello.do 来访问
	public String hello() {
		System.out.println("hello");
		return "login";       //返回    前缀  + login + 后缀   返回/pages/view/login.html页面   
	}
}
