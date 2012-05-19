package com.gaojice.diskviewer.processor;

import java.io.File;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.util.ReflectionUtils;

public class FileProcessorTest {

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Field findField = ReflectionUtils.findField(FileProcessor.class, "diskFileDao");
		findField.setAccessible(true);
	}

	@Test
	public void process() {
		new File(FileProcessorTest.class.getResource("/root/file").getFile()).getParentFile();
	}
}
