package it.polimi.ingsw.GC_21.CLIENT;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;

public class CheckLoginMessage extends MessageToClient {
	private ArrayList<String> games;

	public CheckLoginMessage(boolean result, String string, ArrayList<String> games) {
		super(result, false, string);
		this.games = games;
	}
	
	public CheckLoginMessage (String string) {
		super(true, false, string);	
		}

	@Override
	public InputForm executeCLI(Scanner keyboard) {
		super.executeCLI(keyboard);
		if (result) {
			LobbyInput lobbyInput = new LobbyInput();
			lobbyInput.chooseGame(keyboard);
			return lobbyInput;
		}
		else {
			StartMessage retryStartMessage = new StartMessage();
			return retryStartMessage.executeCLI(keyboard);
		}
	}
	
	
	public ArrayList<String> getGames() {
		return games;
	}

	public void setGames(ArrayList<String> games) {
		this.games = games;
	}

	

	

	
	
	

}
