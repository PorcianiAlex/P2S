package it.polimi.ingsw.GC_21.view;

import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClientInterface;

public class RmiAdapter implements Adapter{

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

	@Override
	public void out(String string) {
		try {
			rmiClient.clientReceive(string);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
