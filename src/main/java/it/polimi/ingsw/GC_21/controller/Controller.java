package it.polimi.ingsw.GC_21.controller;

import java.util.ArrayList;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Controller {


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


	public boolean updateModel(Action action) {
		action.Execute();
		return true;
		//TO DO: EXECUTE MUST RETURN A BOOLEAN, SO THAT THE CONTROLLER KNOWS IF IT WENT WELL OR NOT.
	}
	
}
