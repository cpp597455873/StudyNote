package com.cpp.spring.jdbc.txannotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Cash implements ICash {
	
	@Autowired
	IBookShopService bookShopService;

	@Transactional   //开启事物
	@Override
	public void buyBook(String username, List<Integer> bookidList) {
		for (Integer bookid : bookidList) {
			bookShopService.buyBook(username, bookid);  //若一个购买失败，所有的都购买失败
		}
	}

}
