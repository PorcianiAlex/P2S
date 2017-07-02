package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;


public class Turn implements Serializable{

	private int turnNumber;
	private Game game;
	private Player blackPlayer;
	private ArrayList<Player> turnOrder;

	public Turn(int turnNumber, Game game) {
		this.turnNumber = turnNumber;
		this.game =game;
	}
	
	public void executeView() {
		turnOrder = game.getBoard().getCouncilPalace().getTurnOrder();
		blackPlayer = game.getSpecificPlayer(Color.Black);
		ArrayList<Player> playersInGame = game.getPlayers();
		if (blackPlayer != null) {
			playersInGame.remove(blackPlayer);//for black player there is a specific notify turn in which he plays all his family members at the beginning 
		}
		for (int j = 0; j < playersInGame.size(); j++) {
			if (!turnOrder.contains(playersInGame.get(j))) {
				turnOrder.add(playersInGame.get(j));
			}
		}
		game.resetPlayedLeaders();
		game.notifyOrderedTurn(turnOrder, blackPlayer);
		game.generateRanking();
	}


	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public ArrayList<Player> getTurnOrder() {
		return turnOrder;
	}

	public void setTurnOrder(ArrayList<Player> turnOrder) {
		this.turnOrder = turnOrder;
	}

	
}