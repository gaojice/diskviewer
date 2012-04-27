package com.gaojice.diskviewer.processor;

import java.io.File;

import org.junit.Test;

public class FileProcessorTest {

	private FileProcessor fileProcessor = new FileProcessor();

	@Test
	public void process() {
		File root = new File(FileProcessorTest.class.getResource("/root/file").getFile()).getParentFile();
		fileProcessor.processFile(root, null);
	}
}
