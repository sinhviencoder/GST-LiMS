package com.lims.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookId;
	@NotEmpty(message = "Không được để trống name")
	private String name;
	@ManyToOne
	@JoinColumn(name = "authorId")
	private Author author;
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	private int count;
	private String image;
	private String description;
	private int status;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", author=" + author + ", category=" + category
				+ ", count=" + count + ", image=" + image + ", description=" + description + ", status=" + status + "]";
	}

}
