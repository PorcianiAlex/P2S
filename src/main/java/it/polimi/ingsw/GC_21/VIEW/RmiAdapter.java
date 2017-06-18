package it.polimi.ingsw.GC_21.VIEW;

import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClientInterface;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerForm;

public class RmiAdapter implements AdapterConnection{

	private RmiClientInterface rmiClient; 
	
	public RmiAdapter(RmiClientInterface rmiClient) {
		super();
		this.rmiClient = rmiClient;
	}

	@Override
	public String in() {
		try {
			return rmiClient.sendToServer();
		} catch (RemoteException e) {
			e.printStackTrace();
			return "errore";
		}
	}

	public RmiClientInterface getRmiClient() {
		return rmiClient;
	}

	public void setRmiClient(RmiClientInterface rmiClient) {
		this.rmiClient = rmiClient;
	}

	@Override
	public void out(String string) {
		try {
			rmiClient.clientReceive(string);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendObject(MessageToClient message) {
		try {
			rmiClient.receiveObject(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

	@Override
	public InputForm receiveObject() {
		try {
			return rmiClient.sendObjectToServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
		
	}
	
	

}
