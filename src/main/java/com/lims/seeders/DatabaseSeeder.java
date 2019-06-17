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
import com.lims.entity.Author;
import com.lims.entity.Book;
import com.lims.entity.Category;
import com.lims.entity.Role;
import com.lims.entity.User;
import com.lims.repository.AuthorRepository;
import com.lims.repository.BookRepository;
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
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

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
		seedBooksTable();
	}

	private void seedBooksTable() {
		Book bookCheck = bookRepository.findTopByOrderByNameDesc();
		if (bookCheck == null) {

			Author author1 = new Author();
			author1.setName("Nguyễn Nhật Ánh");
			authorRepository.save(author1);
			
			Category category1 = new Category();
			category1.setName("Văn Học");
			categoryRepository.save(category1);
			
			Book book1 = new Book();
			book1.setName("Tôi thấy hoa vàng trên cỏ xanh");
			book1.setQuantity(100);
			book1.setQuantityActual(100);
			book1.setAuthor(author1);
			book1.setCategory(category1);
			bookRepository.save(book1);

			Author author2 = new Author();
			author2.setName("Tony");
			authorRepository.save(author2);
			Category category2 = new Category();
			category2.setName("Tiểu Thuyết");
			categoryRepository.save(category2);
			Book book2 = new Book();
			book2.setName("Tony Buổi Sáng");
			book2.setQuantity(100);
			book2.setQuantityActual(100);
			book2.setAuthor(author2);
			book2.setCategory(category2);
			bookRepository.save(book2);
			
			Author author3 = new Author();
			author3.setName("Tony Buổi Sáng");
			authorRepository.save(author3);
			Category category3 = new Category();
			category3.setName("Kiến Trúc");
			categoryRepository.save(category3);
			Book book3 = new Book();
			book3.setName("Tôi thấy hoa vàng trên cỏ xanh");
			book3.setQuantity(100);
			book3.setQuantityActual(100);
			book2.setAuthor(author2);
			book2.setCategory(category2);
			bookRepository.save(book2);

		}
	}

	private void seedCategorysTable() {

		Category categoryCheck = categoryRepository.findTopByOrderByNameDesc();

		if (categoryCheck == null) {
			Category category1 = new Category();
			category1.setName("Văn Học");
			categoryRepository.save(category1);

			Category category2 = new Category();
			category2.setName("Kiến Trúc");
			categoryRepository.save(category2);

			Category category3 = new Category();
			category3.setName("Tiểu thuyết");
			categoryRepository.save(category3);

			Category category4 = new Category();
			category4.setName("Khoa Học");
			categoryRepository.save(category4);

			Category category5 = new Category();
			category5.setName("Toán Học");
			categoryRepository.save(category5);

			Category category6 = new Category();
			category6.setName("Kinh Tế");
			categoryRepository.save(category6);

			Category category7 = new Category();
			category7.setName("Mỹ Thuật");
			categoryRepository.save(category7);

		}
	}

	private void seedRolesTable(String roleName) {

		Role r = roleRepository.findByName(roleName);

		if (r == null) {
			Role roleAdmin = new Role();
			roleAdmin.setName(AuthoritiesConstants.ADMIN);
			roleRepository.save(roleAdmin);

			Role roleEmployee = new Role();
			roleEmployee.setName(AuthoritiesConstants.EMPLOYEE);
			roleRepository.save(roleEmployee);
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
