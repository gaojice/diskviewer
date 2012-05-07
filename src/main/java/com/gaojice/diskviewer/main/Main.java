package com.gaojice.diskviewer.main;

import java.io.File;
import java.io.StringReader;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gaojice.diskviewer.processor.FileProcessor;

public class Main {
	public static void main(String[] args) throws BeansException, SQLException {
		String root = "c:/";
		if (ArrayUtils.isNotEmpty(args)) {
			root = args[0];
		}
		System.out.println(root);
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ScriptRunner scriptRunner = new ScriptRunner(applicationContext.getBean(DataSource.class).getConnection());
		scriptRunner.runScript(new StringReader("create table DISK_FILE(  id           varchar2(100),  createdate   date,  lastmodified date,  name         varchar2(500),  type         varchar2(1),  size1         number(11),  parent       varchar2(100));"));
		FileProcessor fileProcessor = applicationContext.getBean(FileProcessor.class);
		fileProcessor.processFile(new File(root), null);
	}
}
