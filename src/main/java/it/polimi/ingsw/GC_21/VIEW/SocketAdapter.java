package it.polimi.ingsw.GC_21.VIEW;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;

public class SocketAdapter implements AdapterConnection{
	private RemoteView remoteView;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

        
	public SocketAdapter(Socket socket, RemoteView remoteView) throws IOException {
		this.socket = socket;
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		this.remoteView = remoteView;

	}
	
	public SocketAdapter(Socket socket) throws IOException {
		this.socket = socket;
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());

	}
	

	@Override
	public void close() throws IOException {
		try {
		socket.close();
		}
		catch(SocketException e){
			return;
		}
	}

	@Override
	public void sendObject(MessageToClient message) {
		try {
			oos.writeObject(message);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			return;
		}
	}

	@Override
	public InputForm receiveObject() {
		try {
			InputForm inputForm = (InputForm) ois.readObject();
			if (inputForm == null) {
				return receiveObject();
			}
			return inputForm;
		} catch (ClassNotFoundException | IOException | ClassCastException e) {
			if (!remoteView.isDisconnected()){
				remoteView.setDisconnected(true);
				MessageToClient disconnectionMessage = new MessageToClient(true, remoteView.getUsername() + " disconnected!");
				try {
					remoteView.getGame().notifyBroadcast(disconnectionMessage);
				} catch (NullPointerException e1) {
				}
			}
			return new PassInput();//if client is disconnected, do a default pass action
		}
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public RemoteView getRemoteView() {
		return remoteView;
	}

	public void setRemoteView(RemoteView remoteView) {
		this.remoteView = remoteView;
	}
	
	

}
