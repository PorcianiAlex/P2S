package it.polimi.ingsw.GC_21.controller;

import java.util.ArrayList;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class Controller {


	private Game modelGame;
	

	public Controller(Game modelGame) {
		this.modelGame = modelGame;
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
