package com.lims.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.Order;
import com.lims.service.AuthorService;
import com.lims.service.CategoryService;
import com.lims.service.OrderService;

@Controller
@RequestMapping(value = "/admin/order")
public class AdminOrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String pageAuthor() {
		return "admin/admin-order";
	}

	@RequestMapping(value = "/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<Order> getDatatable(@Valid DataTablesInput input) {
		return orderService.getOrderAll(input);
	}
}
