package com.lims.controller.admin;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.RequestBuyBook;
import com.lims.service.RequestService;

@Controller
public class AdminRequestBuyBookController {
	@Autowired
	RequestService requestService;

	@RequestMapping(value = "/admin-request-buy-book", method = RequestMethod.GET)
	public String listRequestBuyBook(Model model) {
		model.addAttribute("request-buy-book", requestService.getRequestBuyBookdAll());
		return "admin/admin-request-buy-book";
	}

	@RequestMapping(value = "/admin/request/book", method = RequestMethod.GET)
	public String pageRequestBook() {
		return "admin/admin-request-buy-book";
	}

	@RequestMapping(value = "/admin-request-buy-book/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<RequestBuyBook> getDatatable(@Valid DataTablesInput input) {
		return requestService.getRequestBuyBookAll(input); // tra ve Json
	}

	@RequestMapping(value = "/admin-request-buy-book/update-status", method = RequestMethod.POST)
	@ResponseBody
	public String updateStatusRBB(@RequestBody RequestBuyBook rbbRequedt) {

		// getRBB(id);
		// setStatus
		// updateRBB

		JSONObject jsonObject = new JSONObject();
		try {

			RequestBuyBook rbb0 = requestService.getRequestBuyBookById(rbbRequedt.getRequestBuyBookId()).get();
		
			rbb0.setStatus(rbbRequedt.getStatus());
			long a = rbb0.getRequestBuyBookId();
			System.out.println("Settt"+a);
			RequestBuyBook rbb1 = requestService.updateRequestByID(rbb0);
			
			//jsonObject.put("status", "success");
			jsonObject.put("messages",
					"# " + rbb1.getRequestBuyBookId() + "\t" + rbb1.getBookName() + " update status success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();

	}
}
