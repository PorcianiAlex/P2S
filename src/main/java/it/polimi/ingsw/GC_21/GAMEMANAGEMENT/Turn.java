package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;


public class Turn implements Serializable{

	private int turnNumber;
	private Game game;
	

	public Turn(int turnNumber, Game game) {
		this.turnNumber = turnNumber;
		this.game =game;
	}
	
	public void executeView(ArrayList<Player> turnOrder) {
		game.resetPlayedLeaders();
		game.notifyOrderedTurn(turnOrder, game.getCurrentPlayerNumber());
		game.generateRanking();
	}


	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	
	

	
}