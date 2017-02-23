package dev.source.connection.server;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends Thread {
	private Socket socket;
	private String clientName;
	private volatile boolean isServerRunning;

	public ClientConnectionThread(Socket socket) {
		this.socket = socket;
		isServerRunning = true;
	}

	@Override
	public void run() {
		while (isServerRunning) {

		}
	}

	public void stopClientThread() {
		isServerRunning = false;
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public String getClientName() {
		return clientName;
	}

	public boolean isServerRunning() {
		return isServerRunning;
	}

}
