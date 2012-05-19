package com.gaojice.diskviewer.main;

import java.io.File;
import java.sql.SQLException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.gaojice.diskviewer.dao.DiskFileDao;
import com.gaojice.diskviewer.processor.FileProcessor;

public class Main {
	public static void main1(String[] args) throws BeansException, SQLException {
		String root = "c:/";
		if (ArrayUtils.isNotEmpty(args)) {
			root = args[0];
		}
		System.out.println(root);
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ThreadPoolTaskExecutor taskExecutor = applicationContext.getBean(ThreadPoolTaskExecutor.class);
		FileProcessor fileProcessor = new FileProcessor(applicationContext.getBean(DiskFileDao.class), new File("c:/"), null, taskExecutor);
		taskExecutor.execute(fileProcessor);
		while (true) {
			int size = taskExecutor.getThreadPoolExecutor().getQueue().size();
			System.out.println(size);
			Thread.sleep(5000);
		}

	}

}
