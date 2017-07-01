package it.polimi.ingsw.GC_21.VIEW;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;

public class SocketAdapter implements AdapterConnection{

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

        
	public SocketAdapter(Socket socket) throws IOException {
		this.socket = socket;
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());


	}
	

	@Override
	public void close() throws IOException {
		socket.close();
		oos.close();
		ois.close();
	}

	@Override
	public void sendObject(MessageToClient message) {
		try {
			oos.writeObject(message);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			e.printStackTrace();
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
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return new PassInput();//if client is disconnected, do a default pass action
		}
	}
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
