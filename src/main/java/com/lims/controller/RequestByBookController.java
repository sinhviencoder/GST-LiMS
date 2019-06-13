package com.lims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.RequestBuyBook;
import com.lims.repository.RequestBuyBookRepository;

@Controller
@RequestMapping(value = "/")
public class RequestByBookController {

	@Autowired
	RequestBuyBookRepository requestBuyBookRepository;

	@RequestMapping(value = "demo/rbb", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<RequestBuyBook> demo() {
		Iterable<RequestBuyBook> rbbs = requestBuyBookRepository.findAll();
		for (RequestBuyBook rbb : rbbs) {
			System.out.println(rbb.getDescription());
			System.out.println(rbb.getBook().getName());
		}
		return rbbs;
	}

}