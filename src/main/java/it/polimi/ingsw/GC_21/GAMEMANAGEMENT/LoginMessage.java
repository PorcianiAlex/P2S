package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

public class LoginMessage extends Message{
	private String username;
	private String password;
	private boolean insert;
	

	public LoginMessage(String username, String password, boolean insert) {
		this.username = username;
		this.password = password;
		this.insert = insert;
	}

	@Override
	public boolean convert()  {
		boolean ok = false;
		try {
			ok = controller.getControllerManager().Login(username, password, insert);
			if(ok == false && insert == true) {
				controller.getRemoteView().getAdapter().out("this username already exists!");
			}
			if(ok == false && insert == false) {
				controller.getRemoteView().getAdapter().out("these username and password doesn't exist!");
			}
			if(!checkLoggedUsers(username) && insert==false) {
				CheckLoginMessage checkLoginMessage = new CheckLoginMessage(false, "Oh grullo! tu sei già loggato!", null);
				controller.getRemoteView().getAdapter().sendObject(checkLoginMessage);
				controller.getRemoteView().inputObject();
			}
			if (ok == false) {
				CheckLoginMessage checkLoginMessage = new CheckLoginMessage(false, "Login Error", null);
				controller.getRemoteView().getAdapter().sendObject(checkLoginMessage);
				controller.getRemoteView().inputObject();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		CheckLoginMessage checkLoginMessage = new CheckLoginMessage(true, "Login ok", findGames());
		controller.getRemoteView().getAdapter().sendObject(checkLoginMessage);
		controller.getRemoteView().inputObject();
		return true;
		
	}
		
	public boolean checkLoggedUsers(String name) { 
	    for (int i = 0; i < controller.getControllerManager().getRemoteViews().size(); i++) { 
	      if(name.equals(controller.getControllerManager().getRemoteViews().get(i).getUsername())){ 
	        controller.getRemoteView().getAdapter().out("Oh grullo! tu sei già loggato!"); 
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
		System.out.println(gamesString.toString());
		return gamesString;
	}
	
}



	
	


