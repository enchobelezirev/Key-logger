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

	private List<ClientConnectionThread> clients;
	private ServerSocket serverSocket;

	private static final String SERVER_SHUTDOWN_NOTIFICATION = "|> SERVER SHUT DOWN";
	private static final String SERVER_RUNNING_NOTIFICATION = "|> THE SERVER IS RUNNING ON PORT ";
	private static final String SERVER_INITIALIZING_NOTIFICATION = "|> SERVER INITIALIZING...";

	public Server(int serverPort) {
		clients = new LinkedList<ClientConnectionThread>();
		clientNames = new HashSet<>();
		clientNames.add(Client.getClientUniqueUsername());
		serverInit(serverPort);
	}

	private void serverInit(int serverPort) {
		System.out.println(SERVER_INITIALIZING_NOTIFICATION);
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(SERVER_RUNNING_NOTIFICATION + serverPort);
	}

	public void start() throws Exception {
		while (true) {
			Socket clientSocket = serverSocket.accept();
			ClientConnectionThread clientConnectionThread = new ClientConnectionThread(clientSocket);
			clients.add(clientConnectionThread);
			System.out.println("\t |-> Client " + clientConnectionThread.getClientName() + " with IP "
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
		System.out.println(SERVER_SHUTDOWN_NOTIFICATION);
	}

	public static void main(String[] args) {
		try (Server server = new Server(SERVER_PORT)) {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
