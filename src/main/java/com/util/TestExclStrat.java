package com.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/*Dùng bỏ thuộc tính của đối tượng không muốn chuyển Gson
 * @OneToMany gây ra vong lặp vô hạn
 * @thộc tính khong cần thiết
 * */
public class TestExclStrat implements ExclusionStrategy {

	private Class<?> c;
	private String fieldName;

	public TestExclStrat(String fqfn) throws SecurityException, NoSuchFieldException, ClassNotFoundException {
		this.c = Class.forName(fqfn.substring(0, fqfn.lastIndexOf(".")));
		this.fieldName = fqfn.substring(fqfn.lastIndexOf(".") + 1);
	}

	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}

	public boolean shouldSkipField(FieldAttributes f) {

		return (f.getDeclaringClass() == c && f.getName().equals(fieldName));
	}

}