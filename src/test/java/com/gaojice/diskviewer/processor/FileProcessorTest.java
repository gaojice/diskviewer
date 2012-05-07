package com.gaojice.diskviewer.processor;

import java.io.File;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.ReflectionUtils;

import com.gaojice.diskviewer.dao.DiskFileDao;

public class FileProcessorTest {

	private FileProcessor fileProcessor = new FileProcessor();
	@Mock
	private DiskFileDao diskFileDao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Field findField = ReflectionUtils.findField(FileProcessor.class, "diskFileDao");
		findField.setAccessible(true);
		ReflectionUtils.setField(findField, fileProcessor, diskFileDao);
	}

	@Test
	public void process() {
		File root = new File(FileProcessorTest.class.getResource("/root/file").getFile()).getParentFile();
		fileProcessor.processFile(root, null);
	}
}
