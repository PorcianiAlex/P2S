package it.polimi.ingsw.GC_21.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.LoginMessage;
import it.polimi.ingsw.GC_21.controller.ControllerManager;

public class LobbyInput extends InputFromView{

	private boolean created = false;
	private int joined;
	private ControllerManager controllerManager;
	
	
	
	public LobbyInput() {
		super();
	}

	public LobbyInput(boolean created, int joined) {
		super();
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
			if (CLI) {
				chooseGame(controllerManager);
			}
			
			LobbyMessage checkLobbyMessage = new LobbyMessage(created, joined);
			remoteView.notifyMessage(checkLobbyMessage);

	}
	
	public void chooseGame(ControllerManager controllerManager) {
		 adapterConnection.out("Hi, welcome to our Lobby!");
		 adapterConnection.out("\nPress 'C' to create a game or enter the number of the match you want to join:\n" + controllerManager.getGames().toString());
			   String choice = adapterConnection.in(); 
			   if(choice.equals("C")) {
				   setCreated(true);
			   } else {
				   setJoined(Integer.parseInt(choice));
			   }
	}

}
