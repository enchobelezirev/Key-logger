package dev.source.connection.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import dev.source.connection.client.Client;

public class Server implements AutoCloseable {
	public static final int SERVER_PORT = 10513;
	public static Set<String> clientNames;

	private ServerSocket serverSocket;
	private List<ClientConnectionThread> clients;

	public Server(int serverPort) {
		clients = new LinkedList<ClientConnectionThread>();
		clientNames = new HashSet<>();
		clientNames.add(Client.getClientUniqueUsername());
		serverInit(serverPort);
	}

	private void serverInit(int serverPort) {
		System.out.println("Server initializing...");
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("The server is running on port " + serverPort);
	}

	public void start() throws Exception {
		while (true) {
			Socket clientSocket = serverSocket.accept();
			ClientConnectionThread clientConnectionThread = new ClientConnectionThread(clientSocket);
			clients.add(clientConnectionThread);
			System.out.println("Client " + clientConnectionThread.getClientName() + " with IP "
					+ clientConnectionThread.getSocket().getInetAddress() + " connected to the server!");
			clientConnectionThread.setDaemon(true);
			clientConnectionThread.start();
		}
	}

	@Override
	public void close() throws Exception {
		if (serverSocket != null) {
			serverSocket.close();
		}
		for (ClientConnectionThread clientConnectionThread : clients) {
			clientConnectionThread.stopClientThread();
		}
		clients.clear();
		clientNames.clear();
	}

	public static void main(String[] args) {
		try (Server server = new Server(SERVER_PORT)) {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
