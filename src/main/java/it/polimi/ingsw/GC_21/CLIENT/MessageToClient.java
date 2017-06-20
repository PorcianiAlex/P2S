package it.polimi.ingsw.GC_21.CLIENT;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.PassInput;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public abstract class MessageToClient implements Serializable{
	protected boolean result;
	protected String description;
	protected GameEndState gameEndState = GameEndState.Going;
	protected static boolean outOfTime;
	protected InputForm inputFormToSend;
	
	public MessageToClient(boolean result, String description) {
		this.result = result;
		this.description = description;
	}
	
	public InputForm executeCLI(Scanner keyboard) {
		System.out.println(description);
		return inputFormToSend;
	}
	
	public Timer startTimer() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				inputFormToSend = new PassInput();
			}
		};
		Timer timer = new Timer(true);
		timer.schedule(task, 10*1000);
		return timer;
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
		
	}

}
