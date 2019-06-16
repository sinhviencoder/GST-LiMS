package com.trinh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lims.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
@Autowired
BookService bookService;
	@Test
	public void contextLoads() {
	}

}
