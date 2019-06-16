package com.lims.controller.admin;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping(value = "/update-status", method = RequestMethod.POST)
	@ResponseBody
	public String updateStatus(@RequestBody Order order) {
		System.out.println(order.getOrderId() + " " + order.getStatus());

		Order orderTmp = orderService.getOrderByOrderId(order.getOrderId()).get();

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "success");
			orderTmp.setStatus(order.getStatus());
			System.out.println(order.getNote());
			if (order.getNote() != null) {
				orderTmp.setNote(order.getNote());
			}
			orderService.save(orderTmp);
			jsonObject.put("messages", "#" + orderTmp.getOrderId() + " Update Thành Công");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
}
