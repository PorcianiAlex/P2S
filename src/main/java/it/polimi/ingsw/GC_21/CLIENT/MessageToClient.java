package it.polimi.ingsw.GC_21.CLIENT;

import java.io.Serializable;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;
import it.polimi.ingsw.GC_21.VIEW.InputForm;

public abstract class MessageToClient implements Serializable{
	protected boolean result;
	protected String description;
	protected GameEndState gameEndState = GameEndState.Going;

	
	
	public MessageToClient(boolean result, String description) {
		this.result = result;
		this.description = description;
	}
	
	public InputForm executeCLI(Scanner keyboard) {
		System.out.println(description);
		return null;
	}
	
	public boolean isResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public GameEndState getGameEndState() {
		return gameEndState;
	}

	public void setGameEndState(GameEndState gameEndState) {
		this.gameEndState = gameEndState;
	}
	
	

}
