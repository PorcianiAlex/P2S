package it.polimi.ingsw.GC_21.controller;

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
import it.polimi.ingsw.GC_21.view.RemoteView;

public class ControlloreManager {

	private ArrayList<Controller> controllers;
	private ArrayList<RemoteView> remoteViews;
	private ArrayList<Game> games;
	private JSONParser parser = new JSONParser();

	
	public ControlloreManager() {
		controllers = new ArrayList<Controller>();
		games = new ArrayList<Game>();
		remoteViews = new ArrayList<RemoteView>();
	}
	
	public ArrayList<Controller> getControllers() {
		return controllers;
	}
	public Game addController() {
		Game game = new Game();
		Controller controller = new Controller(game);
		game.setController(controller);
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
		return games;
	}
	public void addGame(Game game) {
		games.add(game);
	}
	
	public boolean Login(String user, String psw, Boolean insert) throws FileNotFoundException, IOException, ParseException {
		
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
	
	
	
}
