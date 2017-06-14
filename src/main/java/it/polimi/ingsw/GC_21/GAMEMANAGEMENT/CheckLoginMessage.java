package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.util.ArrayList;

public class CheckLoginMessage extends Message {
	private boolean result;
	private String string;
	private ArrayList<String> games;

	public CheckLoginMessage(boolean result, String string, ArrayList<String> games) {
		this.result = result;
		this.string = string;
		this.games = games;
	}
	
	
	public ArrayList<String> getGames() {
		return games;
	}

	public void setGames(ArrayList<String> games) {
		this.games = games;
	}

	

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
	

}
