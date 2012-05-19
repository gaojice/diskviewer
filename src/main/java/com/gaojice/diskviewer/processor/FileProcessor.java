package com.gaojice.diskviewer.processor;

import java.io.File;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.gaojice.diskviewer.dao.DiskFileDao;
import com.gaojice.diskviewer.entity.DiskFile;

public class FileProcessor implements Runnable {
	private DiskFileDao diskFileDao;
	private File root;
	private DiskFile p;
	private org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor taskExecutor;

	public FileProcessor(DiskFileDao diskFileDao, File root, DiskFile p, ThreadPoolTaskExecutor taskExecutor) {
		super();
		this.diskFileDao = diskFileDao;
		this.root = root;
		this.p = p;
		this.taskExecutor = taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public void setDiskFileDao(DiskFileDao diskFileDao) {
		this.diskFileDao = diskFileDao;
	}

	public void setRoot(File root) {
		this.root = root;
	}

	public void setP(DiskFile p) {
		this.p = p;
	}

	public void run() {
		DiskFile diskFile = new DiskFile();
		diskFile.setParent(p);
		diskFile.setName(root.getName());
		if (root.isDirectory()) {
			diskFile.setName(root.getAbsolutePath());
			diskFile.setType("D");
			diskFile.setSize(0L);

			diskFileDao.insert(diskFile);
			File[] children = root.listFiles();
			if (children != null) {
				for (File child : children) {
					FileProcessor fileProcessor = new FileProcessor(diskFileDao, child, diskFile, taskExecutor);
					taskExecutor.execute(fileProcessor);
				}
			}
		} else {
			diskFile.setType("F");
			diskFile.setSize(root.length());
			diskFileDao.insert(diskFile);
		}
	}
}
