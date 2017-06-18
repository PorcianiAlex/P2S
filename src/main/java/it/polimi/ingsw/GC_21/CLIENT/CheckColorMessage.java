package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.view.InputForm;

public class CheckColorMessage extends MessageToClient {

	public CheckColorMessage(boolean result, String description) {
		super(result, description);
	}
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		if (result) {
			return super.executeCLI(keyboard);
		}
		else {//maybe it's better to send a start message from server
			CheckLobbyMessage retryLobbyMessage = new CheckLobbyMessage(true, "Retry");
			return retryLobbyMessage.executeCLI(keyboard);
		}
	}

	
	
}
