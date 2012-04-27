package com.gaojice.diskviewer.entity;

import java.util.Date;
import java.util.UUID;

public class DiskFile {
	private String id = UUID.randomUUID().toString();
	private Date createDate = new Date();
	private Date lastModified = new Date();
	private String name;
	private String type;
	private Long size = 0L;
	private DiskFile parent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public DiskFile getParent() {
		return parent;
	}

	public void setParent(DiskFile parent) {
		this.parent = parent;
	}

}
