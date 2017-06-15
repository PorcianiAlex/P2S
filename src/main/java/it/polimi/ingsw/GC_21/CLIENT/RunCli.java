package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;

public class RunCli {

	private Connections client;
	private Scanner scanner;
	
	
	public RunCli(Connections client) {
		this.client = client;
	}

	public void start() {
		
		while(true) {
			Message message = client.getReceivedMessage();
			if(message.getGameEndState().equals(GameEndState.Over)) {
				break;
			}
		}
		
	}

}
