package it.polimi.ingsw.GC_21.controller;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class ControlloreManager {

	private ArrayList<Controller> controllers;
	private ArrayList<RemoteView> remoteViews;
	private ArrayList<Game> games;
	
	
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
	
	
}
