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
		super.executeCLI(keyboard);
		if (result) {
			if (host) {
				InitGameInput initGameInput = new InitGameInput();
				initGameInput.inputFromCli(keyboard);
				return initGameInput;
			}
			return null;
		}
		else {
			CheckLobbyMessage retryLobbyMessage = new CheckLobbyMessage(true, "Retry");
			return retryLobbyMessage.executeCLI(keyboard);
		}
	}
	public boolean isHost() {
		return host;
	}
	public void setHost(boolean host) {
		this.host = host;
	}

	
	
}
