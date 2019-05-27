package com.lims.entity;

public class Resource {
	public static final String TYPE_FOLDER = "folder";
	public static final String TYPE_IMAGE = "image";
	private String name;
	private String url;
	private String thumbnailUrl;
	private String type;

	public Resource(String name, String url, String thumbnailUrl, String type) {
		super();
		this.name = name;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {

		return "Resource: " + name + "\t" + url + "\t" + thumbnailUrl + "\t" + type;
	}

}
