package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.CLIENT.TurnMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;

public class Turn implements Serializable{

	private int turnNumber;
	private Game game;

	public Turn(int turnNumber, Game game) {
		this.turnNumber = turnNumber;
		this.game =game;
	}
	
	public void executeView() {
		ArrayList<Player> turnOrder = game.getBoard().getCouncilPalace().getTurnOrder();
		ArrayList<Player> playersInGame = game.getPlayers();
		for (int j = 0; j < playersInGame.size(); j++) {
			if (!turnOrder.contains(playersInGame.get(j))) {
				turnOrder.add(playersInGame.get(j));
			}
		}	
		game.notifyTurn();
		game.generateRanking();	
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	
}