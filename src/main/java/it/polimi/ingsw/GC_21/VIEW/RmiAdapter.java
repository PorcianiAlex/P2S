package it.polimi.ingsw.GC_21.VIEW;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClientInterface;

public class RmiAdapter implements AdapterConnection{
	private RemoteView remoteView;
	private RmiClientInterface rmiClient; 
	
	public RmiAdapter(RmiClientInterface rmiClient, RemoteView remoteView) {
		this.rmiClient = rmiClient;
		this.remoteView = remoteView;
	}

	public RmiAdapter(RmiClientInterface rmiClient) {
		this.rmiClient = rmiClient;
	}

	
	
	public RemoteView getRemoteView() {
		return remoteView;
	}

	public void setRemoteView(RemoteView remoteView) {
		this.remoteView = remoteView;
	}

	public RmiClientInterface getRmiClient() {
		return rmiClient;
	}

	public void setRmiClient(RmiClientInterface rmiClient) {
		this.rmiClient = rmiClient;
	}

	
	@Override
	public void sendObject(MessageToClient message) {
		try {
			rmiClient.receiveObject(message);
		} catch (RemoteException e) {
			return;
		}		

	}

	@Override
	public InputForm receiveObject() {
		try {
			return rmiClient.sendObjectToServer();
		} catch (IOException e) {
			if (!remoteView.isDisconnected()){
				remoteView.setDisconnected(true);
				MessageToClient disconnectionMessage = new MessageToClient(true, remoteView.getUsername() + " disconnected!");
				try {
					remoteView.getGame().notifyBroadcast(disconnectionMessage);
				} catch (Exception e1) {
					return new InputForm();
				}
			}
			return new PassInput();//if client is disconnected, do a default pass action
		}	
		
	}


	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	

}
