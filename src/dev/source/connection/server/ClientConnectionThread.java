package dev.source.connection.server;

import java.io.IOException;
import java.net.Socket;

public class ClientConnectionThread extends Thread {
	private Socket socket;
	private String clientName;
	private volatile boolean isServerRunning;

	public ClientConnectionThread(Socket socket, String clientName) {
		this.socket = socket;
		this.clientName = clientName;
		isServerRunning = true;
	}

	@Override
	public void run() {
		while (isServerRunning) {
			// TODO
		}
	}

	public void stopClientThread() {
		isServerRunning = false;
		if (socket != null) {
			try {
				System.out.println(clientName + " , " + socket.getInetAddress() + " disconected!");
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
