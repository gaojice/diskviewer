package com.gaojice.diskviewer.processor;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaojice.diskviewer.dao.DiskFileDao;
import com.gaojice.diskviewer.entity.DiskFile;

@Service
public class FileProcessor {
	@Autowired
	private DiskFileDao diskFileDao;

	public void processFile(File file, DiskFile p) {
		DiskFile diskFile = new DiskFile();
		diskFile.setParent(p);
		diskFile.setName(file.getName());
		if (file.isDirectory()) {
			diskFile.setName(file.getAbsolutePath());
			diskFile.setType("D");
			diskFile.setSize(0L);
			if (diskFile.getParent() == null) {
				diskFile.setParent(new DiskFile());
				diskFile.getParent().setId("");
			}
			diskFileDao.insert(diskFile);
			File[] children = file.listFiles();
			if (children != null) {
				for (File child : children) {
					processFile(child, diskFile);
				}
			}
		} else {
			diskFile.setType("F");
			diskFile.setSize(file.length());
			diskFileDao.insert(diskFile);
			DiskFile parent = diskFile.getParent();
			while (parent != null) {
				parent.setSize(parent.getSize() + diskFile.getSize());
				diskFileDao.update(parent.getSize(), parent.getId());
				parent = parent.getParent();
			}
		}
	}
}
