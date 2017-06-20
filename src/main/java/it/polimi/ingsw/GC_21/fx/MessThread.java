package it.polimi.ingsw.GC_21.fx;

import org.hamcrest.core.IsInstanceOf;

import it.polimi.ingsw.GC_21.CLIENT.Connections;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;

public class MessThread extends Thread{

	private Connections client;
	private FXMLGameController gameController;
	
	public MessThread(Connections client, FXMLGameController gameController) {
		this.client = client;
		this.gameController = gameController;
	}

	@Override
	public void run() {
		this.receiveMess();
	}
	
	public void receiveMess() {
		while(true) {
			System.out.println("attendo un nuovo messaggio dal thread!");
			MessageToClient messageToClient = client.getReceivedMessage();
			messageToClient.executeGUI(gameController);
			
			//if(messageToClient instanceof EndGameMessage){chiudi!}
		}
	}
	
}
