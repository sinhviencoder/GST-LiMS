package com.lims.service.implement;

import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lims.entity.User;
import com.lims.repository.UserRepository;
import com.lims.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public Optional<User> findOne(long id) {
		Optional<User> user = userRepository.findById(id);
		user.get().setPassword(null);
		return user;
	}

	@Override
	public String save(User user) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "success");
			jsonObject.put("title", "Added successfully");
			User userTmp = userRepository.save(user);
			jsonObject.put("messages", "# " + userTmp.getUserId() + "\t" + userTmp.getLastName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();

	}

	@Override
	public String update(User user) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "success");
			jsonObject.put("title", "Update successfully");
			User userTmp = userRepository.save(user);
			jsonObject.put("messages", "# " + userTmp.getUserId() + "\t" + userTmp.getLastName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	public String delete(long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			userRepository.deleteById(id);
			jsonObject.put("status", "success");
			jsonObject.put("message", "User #" + id + " Deleted Successfully.");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
