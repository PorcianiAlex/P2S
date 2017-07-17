package it.polimi.ingsw.GC_21.CONTROLLER;

import java.util.ArrayList;


import it.polimi.ingsw.GC_21.CLIENT.CheckLoginMessage;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class LoginController extends ControllerForm{
	private String username;
	private String password;
	private boolean insert;
	

	public LoginController(String username, String password, boolean insert) {
		this.username = username;
		this.password = password;
		this.insert = insert;
	}

	@Override
	public boolean executeController()  {
		boolean ok = false;
		try {
			CheckLoginMessage checkLoginMessage;
			boolean possibleReconnection = controller.getControllerManager().gameReconnection(username);
			boolean savedGames = controller.getControllerManager().gameSavedReconnection(username);
			ok = controller.getControllerManager().Login(username, password, insert);
			if(ok == false && insert == true) {
				checkLoginMessage = new CheckLoginMessage(false, "this username already exists!");
			}
			else if(ok == false && insert == false) {
				checkLoginMessage = new CheckLoginMessage(false, "these username and password doesn't exist!");

			}
			else if(!checkLoggedUsers(username) && insert==false && !possibleReconnection) {//username already logged but with no possible reconnection  
				 checkLoginMessage = new CheckLoginMessage(false, "Oh grullo! tu sei già loggato!");
				
			}
			else if (ok == false) {
				 checkLoginMessage = new CheckLoginMessage(false, "Login Error");
				
			}
			else {
				ArrayList<String> games = findGames();
				String lobbyMessage = "Hi, welcome to our Lobby! \nPress 'C' to create a game or enter the number of the match you want to join:\n" + games.toString();
				checkLoginMessage = new CheckLoginMessage(true, lobbyMessage, games, possibleReconnection, savedGames);
				 

			}
			controller.getRemoteView().getAdapter().sendObject(checkLoginMessage);
			controller.getRemoteView().inputObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
		
	public boolean checkLoggedUsers(String name) { 
	    for (int i = 0; i < controller.getControllerManager().getRemoteViews().size(); i++) { 
	      if(name.equals(controller.getControllerManager().getRemoteViews().get(i).getUsername())){ 
	        return false; 
	      } 
	    } 
	    return true; 
	  }
	
	public ArrayList<String> findGames() {
		ArrayList<String> gamesString = new ArrayList<String>();
		gamesString.add("Games:");
		ArrayList<Game> gamesInLobby = controller.getControllerManager().getGames();
		for (int i = 0; i < gamesInLobby.size(); i++) {
			Game game = gamesInLobby.get(i);
			gamesString.add(game.toString());

		}
		return gamesString;
	}
	
}



	
	


