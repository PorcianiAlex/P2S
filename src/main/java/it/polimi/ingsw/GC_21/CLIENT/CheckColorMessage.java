package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InitGameInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;

public class CheckColorMessage extends MessageToClient {
	private boolean host;

	public CheckColorMessage(boolean result, String description, boolean host) {
		super(result, description);
		this.host = host;
	}
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		if (result) {
			super.executeCLI(keyboard);
			if (host) {
				InitGameInput initGameInput = new InitGameInput();
				initGameInput.inputFromCli(keyboard);
				return initGameInput;
			}
			return null;
		}
		else {//maybe it's better to send a start message from server
			CheckLobbyMessage retryLobbyMessage = new CheckLobbyMessage(true, "Retry");
			return retryLobbyMessage.executeCLI(keyboard);
		}
	}

	
	
}
