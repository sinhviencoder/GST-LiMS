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
			// author 7
			Author author7 = new Author();
			author7.setName("Du Phong");
			authorRepository.save(author7);
			//author 4
			Author author4 = new Author();
			author4.setName("Minh Mẫn");
			authorRepository.save(author4);
// author 5
			Author author5 = new Author();
			author5.setName("Ngô Thiện");
			authorRepository.save(author5);
			// author 6
			Author author6 = new Author();
			author6.setName("Linh");
			authorRepository.save(author6);
			// category1
			Category category1 = new Category();
			category1.setName("Văn Học");
			categoryRepository.save(category1);
			//category 4
			Category category4 = new Category();
			category4.setName("Toán học");
			categoryRepository.save(category4);
			// category5
			Category category5 = new Category();
			category5.setName("Khoa học");
			categoryRepository.save(category5);
			
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
			book3.setName("Những gì đã qua đừng nghĩ lại quá nhiều");
			book3.setQuantity(100);
			book3.setQuantityActual(100);
			book3.setAuthor(author7);
			book3.setCategory(category1);
			book3.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book3);
// book 4
			Book book4 = new Book();
			book4.setName("Thanh xuân không hối tiếc");
			book4.setQuantity(100);
			book4.setQuantityActual(100);
			book4.setAuthor(author7);
			book4.setCategory(category1);
			book4.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book4);
			//book 5
			Book book5 = new Book();
			book5.setName("Toán cao cấp a1");
			book5.setQuantity(100);
			book5.setQuantityActual(100);
			book5.setAuthor(author5);
			book5.setCategory(category4);
			book5.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book5);
			//book 6
			Book book6 = new Book();
			book6.setName("Toán cao cấp A2");
			book6.setQuantity(100);
			book6.setQuantityActual(100);
			book6.setAuthor(author5);
			book6.setCategory(category4);
			book6.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book6);
			//book 7
			Book book7 = new Book();
			book7.setName("Toán cao cấp A3");
			book7.setQuantity(100);
			book7.setQuantityActual(100);
			book7.setAuthor(author5);
			book7.setCategory(category4);
			book7.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book7);
			//book 8
			Book book8 = new Book();
			book8.setName("Lý thuyết đồ thị");
			book8.setQuantity(100);
			book8.setQuantityActual(100);
			book8.setAuthor(author5);
			book8.setCategory(category4);
			book8.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book8);
			// book 9
			Book book9 = new Book();
			book9.setName("Bình yên nằm xa lắc đâu đó giữa địa cầu");
			book9.setQuantity(100);
			book9.setQuantityActual(100);
			book9.setAuthor(author6);
			book9.setCategory(category1);
			book9.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book9);
			// book11
			Book book11 = new Book();
			book11.setName("Khoa học máy tính");
			book11.setQuantity(100);
			book11.setQuantityActual(100);
			book11.setAuthor(author5);
			book11.setCategory(category5);
			book11.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book11);
			// book 10
			Book book10 = new Book();
			book10.setName("Rất nhiều điều mình chưa nói với nhau");
			book10.setQuantity(100);
			book10.setQuantityActual(100);
			book10.setAuthor(author6);
			book10.setCategory(category1);
			book10.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book10);
			// book 12
			Book book12 = new Book();
			book12.setName("Nắm tay anh rồi bình yên sẽ tới");
			book12.setQuantity(100);
			book12.setQuantityActual(100);
			book12.setAuthor(author7);
			book12.setCategory(category1);
			book12.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book12);
			// book 13
			Book book13 = new Book();
			book13.setName("Mắt biếc");
			book13.setQuantity(100);
			book13.setQuantityActual(100);
			book13.setAuthor(author1);
			book13.setCategory(category1);
			book13.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book13);
			// book 14
			Book book14 = new Book();
			book14.setName("Cà phê cùng Tony");
			book14.setQuantity(100);
			book14.setQuantityActual(100);
			book14.setAuthor(author2);
			book14.setCategory(category2);
			book14.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book14);
			//book 15
			Book book15 = new Book();
			book15.setName("Trên đường băng");
			book15.setQuantity(100);
			book15.setQuantityActual(100);
			book15.setAuthor(author2);
			book15.setCategory(category2);
			book15.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book15);
			//book 16
			Book book16 = new Book();
			book16.setName("Trái đất tròn, lòng người góc cạnh");
			book16.setQuantity(100);
			book16.setQuantityActual(100);
			book16.setAuthor(author4);
			book16.setCategory(category1);
			book16.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book16);
			//book 17
			Book book17 = new Book();
			book17.setName("Tự yêu");
			book17.setQuantity(100);
			book17.setQuantityActual(100);
			book17.setAuthor(author7);
			book17.setCategory(category1);
			book17.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book17);
			// book 18
			Book book18 = new Book();
			book18.setName("Đừng gọi anh là người yêu cũ");
			book18.setQuantity(100);
			book18.setQuantityActual(100);
			book18.setAuthor(author7);
			book18.setCategory(category1);
			book18.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book18);
			// book 19
			Book book19 = new Book();
			book19.setName("Thức dậy trên mái nhà");
			book19.setQuantity(100);
			book19.setQuantityActual(100);
			book19.setAuthor(author4);
			book19.setCategory(category1);
			book19.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book19);
			// book 20
			Book book20 = new Book();
			book20.setName("Kể từ giờ em hãy sống vì em");
			book20.setQuantity(100);
			book20.setQuantityActual(100);
			book20.setAuthor(author7);
			book20.setCategory(category1);
			book20.setImage("/demos/construction/images/book/2.jpg");
			bookRepository.save(book20);
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
