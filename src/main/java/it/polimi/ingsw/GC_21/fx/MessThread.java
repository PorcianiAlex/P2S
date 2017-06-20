package it.polimi.ingsw.GC_21.fx;

import java.io.IOException;

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
		try {
			this.receiveMess();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void receiveMess() throws ClassNotFoundException, IOException {
		while(true) {
			System.out.println("attendo un nuovo messaggio dal thread!");
			MessageToClient messageToClient = client.getReceivedMessage();
			messageToClient.executeGUI(gameController);
			
			//if(messageToClient instanceof EndGameMessage){chiudi!}
		}
	}
	
}
