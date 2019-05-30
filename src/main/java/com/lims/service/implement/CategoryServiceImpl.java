package com.lims.service.implement;

import java.util.List;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.lims.entity.Category;
import com.lims.repository.CategoryRepository;
import com.lims.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Iterable<Category> getCategoryAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getCategoryById(long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> search(String q) {
		return null;
	}

	@Override
	public String save(Category category) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "success");
			jsonObject.put("title", "Updated successfully");
			Category caTmp = categoryRepository.save(category);
			jsonObject.put("messages", "#" + caTmp.getCategoryId() + "\t" + caTmp.getName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();

	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public String update(Category category) {
		JSONObject jsonObject = new JSONObject();
		try {
			categoryRepository.save(category);
			jsonObject.put("status", "success");
			jsonObject.put("title", "Updated successfully");
			jsonObject.put("messages", "#" + category.getCategoryId() + "\t" + category.getName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();

	}

	@Override
	public String delete(long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			categoryRepository.deleteById(id);
			jsonObject.put("status", "success");
			jsonObject.put("messages", "Category #" + id + " Deleted Successfully.");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	public List<Category> getCategoryTree() {
		return categoryRepository.findByCategoryParentIsNull();
	}

	@SuppressWarnings("unused")
	private Category getCategoryToId(List<Category> categories, long categoryId) {
		for (Category c : categories) {
			if (c.getCategoryId() == categoryId) {
				return c;
			}
		}
		return null;
	}

	@Override
	public DataTablesOutput<Category> findAll(DataTablesInput input) {
		return categoryRepository.findAll(input);
	}

}