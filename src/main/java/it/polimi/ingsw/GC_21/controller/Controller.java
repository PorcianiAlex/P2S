package it.polimi.ingsw.GC_21.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.UTILITIES.MyObserver;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Controller implements MyObserver{


	private Game modelGame;
	private ArrayList<RemoteView> remoteViews;
	

	public Controller(Game modelGame) {
		this.modelGame = modelGame;
	}

	

	public ArrayList<RemoteView> getRemoteViews() {
		return remoteViews;
	}



	public void addRemoteView(RemoteView remoteView) {
		remoteViews.add(remoteView);
	}



	public Game getModelGame() {
		return modelGame;
	}


	public void setModelGame(Game modelGame) {
		this.modelGame = modelGame;
	}



	@Override
	public boolean update(Action action) {
		if (action.checkAction()) {
			action.Execute();
			return true;
		}
		return false;
	}
	
}
