package it.polimi.ingsw.GC_21.CLIENT;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.CreatePlayerInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;

public class CheckLoginMessage extends MessageToClient {
	private ArrayList<String> games;
	private CheckLobbyMessage callMessage;
	private boolean possibleReconnection;
	private boolean savedGames;

	
	public CheckLoginMessage (boolean result, String string) {
		super(result, false, string);	
		}

	public CheckLoginMessage(String string, CheckLobbyMessage checkLobbyMessage) {
		super(true, false, string);
		this.callMessage = checkLobbyMessage;
	}

	public CheckLoginMessage(boolean result, String message, ArrayList<String> games, boolean possibleReconnection,
			boolean savedGames) {
		super(result, false, message);
		this.games = games;
		this.possibleReconnection = possibleReconnection;
		this.savedGames = savedGames;
	}

	@Override
	public InputForm executeCLI(Object LOCK) throws InterruptedException {
		if (result) {
			inputForm = new LobbyInput(possibleReconnection, savedGames);
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
