package it.polimi.ingsw.GC_21.VIEW;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.CONTROLLER.LobbyController;
import it.polimi.ingsw.GC_21.CONTROLLER.LoginController;

public class LobbyInput extends InputForm{
	private boolean created = false;
	private int joined;
	private ControllerManager controllerManager;
	
	
	
	public LobbyInput() {
	}

	public LobbyInput(boolean created, int joined) {
		this.created = created;
		this.joined = joined;
	}

	public boolean isCreated() {
		return created;
	}

	public void setCreated(boolean created) {
		this.created = created;
	}

	public ControllerManager getControllerManager() {
		return controllerManager;
	}

	public void setControllerManager(ControllerManager controllerManager) {
		this.controllerManager = controllerManager;
	}

	public int getJoined() {
		return joined;
	}

	public void setJoined(int joined) {
		this.joined = joined;
	}
	
	@Override
	public void execute(RemoteView remoteView) {		
			super.execute(remoteView);
			LobbyController checkLobbyMessage = new LobbyController(created, joined);
			remoteView.notifyController(checkLobbyMessage);
	}
	
	public void chooseGame(Scanner keyboard) {
			   String choice = keyboard.nextLine(); 
			   if(choice.equals("C")) {
				   setCreated(true);
			   } else {
				   setJoined(Integer.parseInt(choice));
			   }
	}

}
