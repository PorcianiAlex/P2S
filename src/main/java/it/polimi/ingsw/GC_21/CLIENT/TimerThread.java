package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.PassInput;

public class TimerThread extends Thread implements Serializable {
	private Connections client;

	
	@Override
	public void run() {
		try {
			this.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("sono passati 5 sec mando la pass");
		PassInput passInput = new PassInput();
		try {
			client.sendInput(passInput);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Connections getClient() {
		return client;
	}

	public void setClient(Connections client) {
		this.client = client;
	}
	
	

}
