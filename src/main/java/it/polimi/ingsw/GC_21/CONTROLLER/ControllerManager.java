package it.polimi.ingsw.GC_21.CONTROLLER;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Random;

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
	private ArrayList<Game> savedGames;
	private JSONParser parser = new JSONParser();

	
	public ControllerManager() {
		controllers = new ArrayList<Controller>();
		gamesInLobby = new ArrayList<Game>();
		remoteViews = new ArrayList<RemoteView>();
		activeGames = new ArrayList<Game>();
		savedGames = new ArrayList<Game>();
		loadSavedGames();
	}
	
	private void loadSavedGames() {
 	    try {
		File[] files = new File("C:\\Davide\\Poli\\P2S\\src\\main\\java\\it\\polimi\\ingsw\\GC_21\\GAMEMANAGEMENT\\SavedGames").listFiles();
	    for (File file : files) {
	 	    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
	 	    	Game game = (Game) ois.readObject();
	 	    	savedGames.add(game);
	 	    	ois.close();
	    	}
 	    } 
 	   catch (IOException | ClassNotFoundException e) {
	    	e.printStackTrace();
	    }
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
	
	
	public synchronized boolean gameSavedReconnection(String user) {
		for (int i = 0; i < savedGames.size(); i++) {
			if (!activeGames.contains(savedGames.get(i))) {//I cannot load a game which is already active
				ArrayList<Player> players = savedGames.get(i).getPlayers();
				for (int j = 0; j < players.size(); j++) {
					if (user.equals(players.get(j).getName())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public synchronized void saveGame(Game game) {
		game.setNumberOfPlayersActuallyPresent(0);
		savedGames.add(game);
		try {
		Random random = new Random();
		int gameNumber = random.nextInt(100000);
		File file = new File("C:\\Davide\\Poli\\P2S\\src\\main\\java\\it\\polimi\\ingsw\\GC_21\\GAMEMANAGEMENT\\SavedGames\\" + gameNumber + game.getHost() + ".ser");
		FileOutputStream fileOutputStream;
		fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
		oos.writeObject(game);
		oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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

	public ArrayList<Game> getSavedGames() {
		return savedGames;
	}

	public void setSavedGames(ArrayList<Game> savedGames) {
		this.savedGames = savedGames;
	}

	public synchronized Game getMySavedGame(String username) {
		for (int i = 0; i < savedGames.size(); i++) {
			ArrayList<Player> players = savedGames.get(i).getPlayers();
			for (int j = 0; j < players.size(); j++) {
				if (username.equals(players.get(j).getName())) {
					return savedGames.get(i);
				}
			}
		}
		return null;//if I return null here there are some problems before
	}
	
	
	
}
