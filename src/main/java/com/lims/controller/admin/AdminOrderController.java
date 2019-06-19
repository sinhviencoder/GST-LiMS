package com.lims.controller.admin;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.Order;
import com.lims.repository.OrderRepository;
import com.lims.service.AuthorService;
import com.lims.service.CategoryService;
import com.lims.service.OrderService;



@Controller
@RequestMapping(value = "/admin/order")
public class AdminOrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String pageAuthor() {
		return "admin/admin-order";
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

	@RequestMapping(value = "/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<Order> getDatatable(@Valid DataTablesInput input) {
		return orderRepository.findAll(input);
	}

	 private class SalarySpecification implements Specification<Order> {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private final Integer minSalary;
	        private final Integer maxSalary;

	        SalarySpecification(DataTablesInput input) {
	            String salaryFilter = input.getColumn("salary").getSearch().getValue();
	            if (!StringUtils.hasText(salaryFilter)) {
	                minSalary = maxSalary = null;
	                return;
	            }
	            String[] bounds = salaryFilter.split(";");
	            minSalary = getValue(bounds, 0);
	            maxSalary = getValue(bounds, 1);
	        }

	        private Integer getValue(String[] bounds, int index) {
	            if (bounds.length > index && StringUtils.hasText(bounds[index])) {
	                try {
	                    return Integer.valueOf(bounds[index]);
	                } catch (NumberFormatException e) {
	                    return null;
	                }
	            }
	            return null;
	        }

	        @Override
	        public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            Expression<Integer> salary = root.get("salary").as(Integer.class);
	            if (minSalary != null && maxSalary != null) {
	                return criteriaBuilder.between(salary, minSalary, maxSalary);
	            } else if (minSalary != null) {
	                return criteriaBuilder.greaterThanOrEqualTo(salary, minSalary);
	            } else if (maxSalary != null) {
	                return criteriaBuilder.lessThanOrEqualTo(salary, maxSalary);
	            } else {
	                return criteriaBuilder.conjunction();
	            }
	        }
	    }

	    private class ExcludeAnalystsSpecification implements Specification<Order> {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	            return criteriaBuilder.notEqual(root.get("position"), "Analyst");
	        }
	    }
}
