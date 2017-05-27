package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.util.ArrayList;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.view.RemoteView;

public class Turn {

	private int turnNumber;
	private Game game;

	public Turn(int turnNumber, Game game) {
		this.turnNumber = turnNumber;
		this.game =game;
	}
	
	public void executeView() {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			RemoteView view = (RemoteView) game.getViewObserver();
			game.getBoard().printBoard();
			view.input();
			game.getBoard().printBoard();
		}
	}

	
}