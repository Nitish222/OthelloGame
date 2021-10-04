package server;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.util.function.Consumer;

import application.NetworkConnection;

public class Server{
	int socket = 61050;
	public Server(int soc){
		socket = soc;
	}
	public static void main(String args[]) throws IOException {
		ServerSocket socket = new ServerSocket(61050);
	}
}
