package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;


import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.CONTROLLER.LobbyController;

public class LobbyInput extends InputForm{
	private boolean created = false;
	private int joined;
	private ControllerManager controllerManager;
	private boolean reconnection;
	
	
	
	public LobbyInput(boolean reconnection) {
		this.reconnection = reconnection;
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
			LobbyController checkLobbyMessage = new LobbyController(created, joined, reconnection);
			remoteView.notifyController(checkLobbyMessage);
	}
	
	@Override
	public void inputFromCli() throws InterruptedException {
			if (reconnection) {
				System.out.println("You are  already in an active game. Do you want to continue this game? \n(Y) or (N)?");
				String answer = takeInput(this);
				switch (answer) {
				case "Y": reconnection = true;			
					break;
				case "N": reconnection = false;
					chooseOrCreate();
				default: reconnection = false;
					chooseOrCreate();
					break;
				}
			}
			else {
				chooseOrCreate();
			}
		
		}
		
	
		public void chooseOrCreate() throws InterruptedException {
			try{
			String choice = takeInput(this);
			   if(choice.equals("C")) {
				   setCreated(true);
			   } else {
				   setJoined(Integer.parseInt(choice));
			   }
		} catch (NumberFormatException e) {
			System.out.println("Invalid input, choose C or a game number");
			chooseOrCreate();
		}
		}
			  
	

}
