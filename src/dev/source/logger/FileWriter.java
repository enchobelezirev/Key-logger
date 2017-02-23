package dev.source.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileWriter implements AutoCloseable {
	private static final boolean AUTO_FLUSH = true;
	private static FileWriter fileManagerInstance = null;
	private static final boolean ATTRIBUTE_VALUE = true;
	private static final boolean APPEND = true;
	private static final String FILE_LOCATION = "C:\\Users\\Default\\AppData\\log.txt";
	private static PrintWriter printWriter;
	private static File file;

	private FileWriter() {
		// Exists only to defeat instantiation.
	}

	synchronized public static FileWriter getInstance() throws IOException {
		if (fileManagerInstance == null) {
			initialize();
			// hide(file);
			fileManagerInstance = new FileWriter();
		}
		return fileManagerInstance;
	}

	private static void initialize() throws IOException {
		file = new File(FILE_LOCATION);
		if (!file.exists()) {
			file.createNewFile();
		}
		printWriter = new PrintWriter(new FileOutputStream(file, APPEND), AUTO_FLUSH);
	}

	private static void hide(File file) throws IOException {
		Path path = file.toPath();
		Boolean hidden = (Boolean) Files.getAttribute(path, "dos:hidden", LinkOption.NOFOLLOW_LINKS);
		if (hidden != null && !hidden) {
			Files.setAttribute(path, "dos:hidden", ATTRIBUTE_VALUE, LinkOption.NOFOLLOW_LINKS);
		}
	}

	public void log(String string) {
		printWriter.write(string);
	}

	public String getFileLocation() {
		return FILE_LOCATION;
	}

	@Override
	public void close() throws Exception {
		if (printWriter != null) {
			printWriter.close();
		}
	}

}
