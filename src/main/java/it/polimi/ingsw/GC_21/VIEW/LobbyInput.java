package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;


import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.CONTROLLER.LobbyController;

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
			LobbyController checkLobbyMessage = new LobbyController(created, joined);
			remoteView.notifyController(checkLobbyMessage);
	}
	
	public void chooseGame(Scanner keyboard) {
		try {
			 String choice = keyboard.next(); 
			 keyboard.reset();
			   if(choice.equals("C")) {
				   setCreated(true);
			   } else {
				   setJoined(Integer.parseInt(choice));
			   }
		} catch (NumberFormatException e) {
			System.out.println("Invalid input, choose C or a game number");
			chooseGame(keyboard);
		}
			  
	}

}
