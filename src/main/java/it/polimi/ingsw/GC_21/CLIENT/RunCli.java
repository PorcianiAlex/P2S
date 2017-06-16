package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;

public class RunCli {

	private Connections client;
	private Scanner scanner;
	
	
	public RunCli(Connections client) {
		this.client = client;
	}

	public void start() {
		
		while(true) {
			MessageToClient message = client.getReceivedMessage();
			if(message.getGameEndState().equals(GameEndState.Over)) {
				client.getKeyboard().close();
				break;
			}
		}
		
	}

}
