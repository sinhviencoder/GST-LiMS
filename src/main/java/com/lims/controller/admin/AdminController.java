package com.lims.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/")
	public String index() {
		return "admin.homePage";
	}
	
	@GetMapping("/admin/uploads/jquery")
	public String uploadJQuery() {
		return "admin.resource.uploadJQuery";
	}
}
