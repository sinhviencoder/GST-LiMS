package com.lims.seeders;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.lims.config.AuthoritiesConstants;
import com.lims.entity.Category;
import com.lims.entity.Role;
import com.lims.entity.User;
import com.lims.repository.CategoryRepository;
import com.lims.repository.RoleRepository;
import com.lims.repository.UserRepository;

/**
 * Created by TrinhNguyen on 3/12/2018.
 */
@Component
public class DatabaseSeeder {
	private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private CategoryRepository categoryRepository;

	@Autowired

	public DatabaseSeeder(UserRepository userRepository, RoleRepository roleRepository,
			CategoryRepository categoryRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.categoryRepository = categoryRepository;
	}

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		seedRolesTable(AuthoritiesConstants.ADMIN);
		seedRolesTable(AuthoritiesConstants.EMPLOYEE);
		seedUsersTable();
		seedCategorysTable("Tin Tức");
	}

	private void seedCategorysTable(String name) {

		Category categoryCheck = categoryRepository.findTopByOrderByNameDesc();

		if (categoryCheck == null) {
			Category category = new Category();
			category.setName(name);
			categoryRepository.save(category);
		}
	}

	private void seedRolesTable(String roleName) {

		Role r = roleRepository.findByName(roleName);

		if (r == null) {
			Role role = new Role();
			role.setName(roleName);
			roleRepository.save(role);
		}
	}

	private void seedUsersTable() {
		User userCheck = userRepository.findByUsername("admin@gmail.com");

		if (userCheck == null) {
			User user = new User();
			user.setFirstName("Nguyen");
			user.setLastName("Trinh");
			user.setStatus(1);
			user.setUsername("admin@gmail.com");
			user.setPhone("0");
			user.setPassword(new BCryptPasswordEncoder().encode("123456789o"));

			Role roleAdmin = roleRepository.findByName(AuthoritiesConstants.ADMIN);
			Role roleUser = roleRepository.findByName(AuthoritiesConstants.EMPLOYEE);

			Set<Role> roles = new HashSet<>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			user.setRoles(roles);

			userRepository.save(user);
			logger.info("Users Seeded");
		} else {
			logger.trace("Users Seeding Not Required");
		}
	}
}
