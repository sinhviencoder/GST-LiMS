package com.lims.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

/**
 * @author Nguyen Van Trinh
 *
 */
@Entity
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@NotEmpty(message = "Vui lòng nhập First Name")
	private String firstName;
	@NotEmpty(message = "Vui lòng nhập Last Name")
	private String lastName;
	@NotEmpty(message = "Vui lòng nhập Phone")
	private String phone;
	private String avatar;
	@NotEmpty(message = "Vui lòng nhập email hoặc số điện thoại")
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@NotEmpty(message = "Vui lòng nhập password")
	@Column(name = "password", nullable = false)
	private String password;
	private int status;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User() {
		super();
	}

	public User(String password) {
		super();
		this.password = password;
	}

	public User(Long userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}

	public User(Long userId, String username, String password, Set<Role> roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
