package it.polimi.ingsw.GC_21.CLIENT;

import java.io.Serializable;
import java.util.Scanner;


import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;
import it.polimi.ingsw.GC_21.REMOTEVIEW.InputForm;
import it.polimi.ingsw.GC_21.REMOTEVIEW.LobbyInput;
import it.polimi.ingsw.GC_21.REMOTEVIEW.PassInput;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class MessageToClient implements Serializable{
	protected boolean result;
	protected String description;
	protected GameEndState gameEndState = GameEndState.Going;
	protected boolean waiting; //if the message needs an input from the client!
	protected InputForm inputForm;
	protected Connections client;

	
	public boolean isWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	
	public MessageToClient(boolean result, boolean waiting, String description) {
		this.result = result;
		this.waiting = waiting;
		this.description = description;
	}
	
	
	public MessageToClient(boolean result, String description) {
		this.result = result;
		this.waiting = false;
		this.description = description;
	}
	

	
	public InputForm executeCLI(Object LOCK) throws InterruptedException  {//notifies the run CLI that there is a new Input form to fill
		System.out.println(description);
		synchronized (LOCK) {
			LOCK.notifyAll();
		}
		if (inputForm != null) {
			inputForm.inputFromCli();
		}
		return inputForm;
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
	
	public void executeGUI(FXMLGameController gameController) {		
		gameController.printMessDescription(description);
	}

	public InputForm getInputForm() {
		return inputForm;
	}

	public void setInputForm(InputForm inputForm) {
		this.inputForm = inputForm;
	}

	public void setClient(Connections client) {
		this.client = client;

	}
	
}
