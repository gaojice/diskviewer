package com.gaojice.diskviewer.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaojice.diskviewer.entity.DiskFile;

@Service
@Transactional
public class DiskFileDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void update(Long size, Long id) {
		DiskFile diskFile = entityManager.find(DiskFile.class, Long.valueOf(id));
		diskFile.setSize(size);
	}

	public void insert(DiskFile diskFile) {
		entityManager.persist(diskFile);
	}
}
