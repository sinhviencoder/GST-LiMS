package com.lims.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RequestBuyBook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long requestBuyBookId;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book;
	private boolean isApproval;
	private String description;

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

	public boolean isApproval() {
		return isApproval;
	}

	public void setApproval(boolean isApproval) {
		this.isApproval = isApproval;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
