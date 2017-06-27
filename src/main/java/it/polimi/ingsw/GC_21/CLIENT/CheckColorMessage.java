package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InitGameInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;

public class CheckColorMessage extends MessageToClient {
	private boolean host;

	public CheckColorMessage(boolean result, String description, boolean host) {
		super(result, false, description);
		this.host = host;
	}
	
	@Override
	public InputForm executeCLI(Object LOCK) throws InterruptedException {
		if (result) {
			if (host) {	
				inputForm = new InitGameInput();
				return super.executeCLI(LOCK);
			}
			System.out.println(description);
			return null;
		}
		else {
			System.out.println(description);
			CheckLobbyMessage retryLobbyMessage = new CheckLobbyMessage(this, true, "Retry");
			return retryLobbyMessage.executeCLI(LOCK);
		}
	}
	public boolean isHost() {
		return host;
	}
	public void setHost(boolean host) {
		this.host = host;
	}

	
	
}
