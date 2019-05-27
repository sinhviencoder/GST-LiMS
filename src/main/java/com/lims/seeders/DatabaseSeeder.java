package com.lims.seeders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	private JdbcTemplate jdbcTemplate;

	@Autowired

	public DatabaseSeeder(UserRepository userRepository, RoleRepository roleRepository,
			CategoryRepository categoryRepository, JdbcTemplate jdbcTemplate) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.categoryRepository = categoryRepository;
		this.jdbcTemplate = jdbcTemplate;
	}

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		seedRolesTable(AuthoritiesConstants.ADMIN);
		seedRolesTable(AuthoritiesConstants.EMPLOYEE);
		seedUsersTable();
		seedCategorysTable("Tin Tức");
	}

	private void seedCategorysTable(String name) {
		String sql = "SELECT name FROM category c WHERE c.name = \"" + name + "\" LIMIT 1";
		List<Role> r = jdbcTemplate.query(sql, new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				return null;
			}
		});
		if (r == null || r.size() <= 0) {
			Category category = new Category();
			category.setName(name);
			categoryRepository.save(category);
		}
	}

	private void seedRolesTable(String roleName) {
		String sql = "SELECT name FROM role r WHERE r.name = \"" + roleName + "\" LIMIT 1";
		List<Role> r = jdbcTemplate.query(sql, new RowMapper<Role>() {
			@Override
			public Role mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				return null;
			}
		});
		if (r == null || r.size() <= 0) {
			Role role = new Role();
			role.setName(roleName);
			roleRepository.save(role);
		}
	}

	private void seedUsersTable() {
		String sql = "SELECT username FROM user U WHERE U.username = \"admin@gmail.com\" LIMIT 1";
		List<User> u = jdbcTemplate.query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				return null;
			}
		});
		if (u == null || u.size() <= 0) {
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
