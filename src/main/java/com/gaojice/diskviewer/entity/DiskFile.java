package com.gaojice.diskviewer.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DiskFile {

	private Long id;
	private Date createDate = new Date();
	private Date lastModified = new Date();
	private String name;
	private String type;
	private Long size = 0L;
	private DiskFile parent;
	private Set<DiskFile> children = new HashSet<DiskFile>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@ManyToOne(targetEntity = DiskFile.class)
	public DiskFile getParent() {
		return parent;
	}

	public void setParent(DiskFile parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent")
	public Set<DiskFile> getChildren() {
		return children;
	}

	public void setChildren(Set<DiskFile> children) {
		this.children = children;
	}

}
