package com.lims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//${pageContext.request.contextPath}/contactus
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lims.entity.Book;
import com.lims.service.BookService;


@Controller
public class MainController {
@Autowired
BookService bookService;
	@RequestMapping(value = "/")

	public String welcome(Model model,Book book) {
		List<Book> bookTop= bookService.getTopBook();
			
				model.addAttribute("bookTop",bookTop);
				
			
		return "views/index";
	}
//	@RequestMapping(value="/", method = RequestMethod.GET)
//	@ResponseBody
//	public String getTop(Model model, Book book) {
//		List<Book> bookTop= bookService.getTopBook();
//		for (int i = 0; i < bookTop.size(); i++) {
//			model.addAttribute("bookTop",bookTop.get(i));
//			System.out.println(bookTop.get(i).toString());
//		}
//		
//		return"view/index :: #content";
//}
}