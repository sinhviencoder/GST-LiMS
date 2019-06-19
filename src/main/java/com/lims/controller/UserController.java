package com.lims.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lims.entity.Order;
import com.lims.entity.User;
import com.lims.repository.UserRepository;
import com.lims.service.CategoryService;
import com.lims.service.OrderService;
import com.lims.service.UserService;

@Controller
public class UserController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;
	@Autowired
	UserRepository userRepository;

	@RequestMapping("/login")
	public String login(@RequestParam(required = false) String message, final Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		System.out.println(username);

		System.out.println(message);

		if (message != null && !message.isEmpty()) {
			if (message.equals("logout")) {
				model.addAttribute("message", "Logout!");
			}
			if (message.equals("error")) {
				model.addAttribute("message", "Login Failed!");
			}
		}
		return "view/login";
	}

	@RequestMapping(value = { "/user-profile" }, method = RequestMethod.GET)
	public String getUserProfile(Model model, Order order) {
		model.addAttribute("categoryRoots", categoryService.getCategoryRoot());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String username = auth.getName();
		System.out.println(username);
		User user = userRepository.findByUsername(username);
		model.addAttribute("user", user);

		Page<Order> orderPage = orderService.getOrderByUserId(user.getUserId(), PageRequest.of(0, 32));

		model.addAttribute("orderPage", orderPage);

		return "view/user-profile";
	}

	@RequestMapping(value = "/order/cancel", method = RequestMethod.GET)
	public String orderCancel(@Param(value = "orderId") long orderId) {

		Order order = orderService.getOrderByOrderById(orderId);
		order.setStatus(7);

		orderService.save(order);

		return "redirect:/user-profile";

	}

	@RequestMapping(value = "/order/renewal", method = RequestMethod.GET)
	public String orderRenewal(@Param(value = "orderId") long orderId) {

		Order order = orderService.getOrderByOrderById(orderId);

		long getDiff = order.getEndDate().getTime() - order.getStartDate().getTime();
		long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
		System.out.println(getDaysDiff);
		// neu nhu chua gia han lan nao thi duoc gia han them mot lan
		if (getDaysDiff < 30) {
			Calendar c = Calendar.getInstance();
			c.setTime(order.getEndDate());
			c.add(Calendar.DATE, 15);
			order.setEndDate(c.getTime());
			orderService.save(order);
		}
		return "redirect:/user-profile";

	}
}
