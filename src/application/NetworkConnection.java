package application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {
	
	//callback for recieving the message
	//consumer because we are recieving the object
	private Consumer<Serializable> onRecieveCallback;
	private ConnectionThread connectionThread  = new ConnectionThread();
	
	public NetworkConnection(Consumer<Serializable> onRecieveCallback) {
		this.onRecieveCallback = onRecieveCallback;
		connectionThread.setDaemon(true);
	}
	
	public void startConnection() throws Exception{
		connectionThread.start();
	}
	
	public void send(Serializable data) throws Exception{
		connectionThread.output.writeObject(data);
	}
	
	public void closeConnection() throws Exception{
		connectionThread.socket.close();
	}
	
	protected abstract boolean isServer();
	protected abstract String getIP();
	protected abstract int getPort();
	
	private class ConnectionThread extends Thread {
		private Socket socket;
		private ObjectOutputStream output;
		
		@Override
		public void run() {
			try 
				(ServerSocket server = isServer()? new ServerSocket(getPort()) :null;
				Socket socket =  isServer()? server.accept() : new Socket(getIP(),getPort());
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream())){
				this.socket = socket;
				this.output = output;
				//to send message quicker without waiting time buffer
				socket.setTcpNoDelay(true);
				
				//reading connection thread
				while(true) {
					Serializable data = (Serializable) input.readObject();
					onRecieveCallback.accept(data);
				}
			}
			catch(Exception e) {
				onRecieveCallback.accept("Connection Closed");
			}
		}
	}
	
	
}
