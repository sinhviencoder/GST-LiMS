package com.lims.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class RequestBuyBook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long requestBuyBookId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	private long status;
	@NotEmpty(message = "Không được để trống Nhà Xuất Bản")
	private String publisher;
	@NotEmpty(message = "Không được để trống Tên sách")
	private String bookName;
	@NotEmpty(message = "Không được để trống Tên tác giả")
	private String authorName;
	@NotEmpty(message = "Không được để trống thể loại")
	private String categoryName;
	private String note;
	@NotEmpty(message = "Không được để trống số lượng")
	private String quantity;

	public long getRequestBuyBookId() {
		return requestBuyBookId;
	}

	public void setRequestBuyBookId(long requestBuyBookId) {
		this.requestBuyBookId = requestBuyBookId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}
