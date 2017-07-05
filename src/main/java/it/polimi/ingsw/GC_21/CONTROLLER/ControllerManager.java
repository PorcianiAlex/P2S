package it.polimi.ingsw.GC_21.CONTROLLER;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;

public class ControllerManager {

	private ArrayList<Controller> controllers;
	private ArrayList<RemoteView> remoteViews;
	private ArrayList<Game> gamesInLobby;
	private ArrayList<Game> activeGames;
	private JSONParser parser = new JSONParser();

	
	public ControllerManager() {
		controllers = new ArrayList<Controller>();
		gamesInLobby = new ArrayList<Game>();
		remoteViews = new ArrayList<RemoteView>();
		activeGames = new ArrayList<Game>();

	}
	
	public ArrayList<Controller> getControllers() {
		return controllers;
	}
	public synchronized Game createGame(Controller controller) {
		String host = controller.getRemoteView().getUsername();
		Game game = new Game(host);
		controllers.add(controller);
		addGame(game);
		return game;
	}
	public ArrayList<RemoteView> getRemoteViews() {
		return remoteViews;
	}
	public void addRemoteView(RemoteView remoteView) {
		remoteViews.add(remoteView);
	}
	
	public ArrayList<Game> getGames() {
		return gamesInLobby;
	}
	public synchronized void addGame(Game game) {
		gamesInLobby.add(game);
	}
	
	
	public synchronized boolean gameReconnection(String user) {
		for (int i = 0; i < activeGames.size(); i++) {
			ArrayList<Player> players = activeGames.get(i).getPlayers();
			for (int j = 0; j < players.size(); j++) {
				if (user.equals(players.get(j).getName())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public synchronized boolean Login(String user, String psw, Boolean insert) throws FileNotFoundException, IOException, ParseException {
		
		Object obj = parser.parse(new FileReader("Users.json"));
	    JSONObject users = (JSONObject) obj;
	    JSONArray usersarray= (JSONArray) users.get("users");
	    	for (Object o : usersarray) {
           	JSONObject jsonLineItem = (JSONObject) o;       
	    	if(user.equals(jsonLineItem.get("name").toString())){              	
	    		if (psw.equals(jsonLineItem.get("psw").toString()) && !insert) {
	    			return true;
	    		} else {
					return false;
				}
	    	 }
	    	}
		if(insert) {
	    	
			 JSONObject objec = new JSONObject();
		     			
			  JSONObject jsonObj = new JSONObject();
	    	  jsonObj.put("name", user.toString());
	    	  jsonObj.put("psw", psw.toString());
	    	  usersarray.add(jsonObj);
	    	 		       
		      objec.put("users", usersarray);

	    	   File file = new File("Users.json");
	    	   file.createNewFile();
	    	   FileWriter filewriter = new FileWriter(file);
	    	   
	    	   
	    	   try	{
	    		  filewriter.write(objec.toJSONString());
	              filewriter.flush();
	              filewriter.close();

	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	    	
	    	  
	    	return true;
	    
	    }
	return false;
	}

	public synchronized RemoteView getMyActiveRemoteView(String username) {
		for (int i = 0; i < remoteViews.size(); i++) {
			if (remoteViews.get(i).getUsername().equals(username)) {
				return remoteViews.get(i);
			}
		}
		return null;//it should not 
	}

	public ArrayList<Game> getActiveGames() {
		return activeGames;
	}

	public void setActiveGames(ArrayList<Game> activeGames) {
		this.activeGames = activeGames;
	}
	
	
	
}
