package it.polimi.ingsw.GC_21.CLIENT;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.REMOTEVIEW.CreatePlayerInput;
import it.polimi.ingsw.GC_21.REMOTEVIEW.InputForm;
import it.polimi.ingsw.GC_21.REMOTEVIEW.LobbyInput;

public class CheckLoginMessage extends MessageToClient {
	private ArrayList<String> games;
	private CheckLobbyMessage callMessage;
	private boolean possibleReconnection = false;

	public CheckLoginMessage(boolean result, String string, ArrayList<String> games) {
		super(result, false, string);
		this.games = games;
	}
	
	public CheckLoginMessage (String string) {
		super(true, false, string);	
		}

	public CheckLoginMessage(String string, CheckLobbyMessage checkLobbyMessage) {
		super(true, false, string);
		this.callMessage = checkLobbyMessage;
	}

	@Override
	public InputForm executeCLI(Object LOCK) throws InterruptedException {
		if (result) {
			inputForm = new LobbyInput(possibleReconnection);
			if (callMessage != null) {
				callMessage.setInputForm(inputForm);
			}
			return super.executeCLI(LOCK);
		}
		else {
			System.out.println(description);
			StartMessage retryStartMessage = new StartMessage(this);
			return retryStartMessage.executeCLI(LOCK);
		}
	}
	
	
	public ArrayList<String> getGames() {
		return games;
	}

	public void setGames(ArrayList<String> games) {
		this.games = games;
	}

	public boolean isPossibleReconnection() {
		return possibleReconnection;
	}

	public void setPossibleReconnection(boolean possibleReconnection) {
		this.possibleReconnection = possibleReconnection;
	}

	

	

	
	
	

}
