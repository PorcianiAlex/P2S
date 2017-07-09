package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;


import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.CONTROLLER.LobbyController;

public class LobbyInput extends InputForm{
	private boolean created = false;
	private int joined;
	private ControllerManager controllerManager;
	private boolean reconnection;
	private boolean savedGames;
	
	
	public LobbyInput(boolean reconnection, boolean savedGames) {
		this.reconnection = reconnection;
		this.savedGames = savedGames;
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
			LobbyController checkLobbyMessage = new LobbyController(created, joined, reconnection, savedGames);
			remoteView.notifyController(checkLobbyMessage);
	}
	
	@Override
	public void inputFromCli() throws InterruptedException {
			if (reconnection) {
				System.out.println("You are  already in an active game. Do you want to reconnect in this game? \n(Y) or (N)?");
				String answer = takeInput(this);
				switch (answer) {
				case "Y": reconnection = true;			
					break;
				case "N": reconnection = false;
					chooseOrCreate();
					break;
				default: reconnection = false;
					possibleSavedGames();
					break;
				}
			}
			else {
				possibleSavedGames();
			}
		
		}
		
	
		private void possibleSavedGames() throws InterruptedException {
			if (savedGames) {
				System.out.println("You have some saved games. Do you want to continue this game? \n(Y) or (N)?");
				String answer = takeInput(this);
				switch (answer) {
				case "Y": savedGames = true;			
					break;
				case "N": savedGames = false;
					System.out.println("Look at the game you can choose or create (C) a new one");
					chooseOrCreate();
					break;
				default: savedGames = false;
				    System.out.println("Look at the game you can choose or create (C) a new one");
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
