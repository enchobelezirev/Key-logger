package dev.source.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class FileManager {
  private static FileManager fileManagerInstance = null;
  private static final boolean ATTRIBUTE_VALUE = true;
  private static final boolean APPEND = true;
  private static final String FILE_LOCATION = "C:\\Users\\Default\\AppData\\log.txt";
  private static PrintWriter printWriter;
  private static File file;

  private FileManager() {
    // Exists only to defeat instantiation.
  }

  public static FileManager getInstance() throws IOException {
    if (fileManagerInstance == null) {
      initialize();
      hide(file);
      fileManagerInstance = new FileManager();
    }
    return fileManagerInstance;
  }

  private static void initialize() throws IOException {
    file = new File(FILE_LOCATION);
    if (!file.exists()) {
      file.createNewFile();
    }
    printWriter = new PrintWriter(new FileOutputStream(file, APPEND));
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
    printWriter.flush();
  }
}
