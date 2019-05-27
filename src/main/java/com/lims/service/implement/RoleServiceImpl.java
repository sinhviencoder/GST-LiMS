package com.lims.service.implement;

import java.util.List;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lims.entity.Role;
import com.lims.repository.RoleRepository;
import com.lims.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Iterable<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public List<Role> search(String q) {
		return null;
	}

	@Override
	public Optional<Role> findOne(long id) {
		return roleRepository.findById(id);
	}

	@Override
	public String save(Role role) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "success");
			jsonObject.put("title", "Added successfully");
			Role roleTmp = roleRepository.save(role);
			jsonObject.put("messages", "# " + roleTmp.getRoleId() + "\t" + roleTmp.getName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();

	}
	@Override
	public String update(Role role) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "success");
			jsonObject.put("title", "Added successfully");
			Role roleTmp = roleRepository.save(role);
			jsonObject.put("messages", "# " + roleTmp.getRoleId() + "\t" + roleTmp.getName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
		
	}

	@Override
	public String delete(long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			roleRepository.deleteById(id);
			jsonObject.put("status", "success");
			jsonObject.put("messages", "Role #" + id + " Deleted Successfully.");

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}