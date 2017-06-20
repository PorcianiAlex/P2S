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
		for (int i = 0; i < game.getPlayers().size(); i++) {
			game.notifyObservers("Age: "+ game.getCurrentAge().getAgeNumber() + "\nRound: " + game.getCurrentAge().getCurrentRound().getRoundNumber() 
					+"\nTurn: "+turnNumber +"\nTurn of player :"+ game.getPlayers().get(i).getName() +" color: " +game.getPlayers().get(i).getPlayerColor().toString() );
			game.notifyObservers(game.getBoard());
		}
		TurnMessage turnMessage = new TurnMessage(game.getBoard(), game.getPlayers());
		game.notifyMessageToClient(turnMessage);
		game.notifyTurn();
		game.generateRanking();	
		System.out.println(game.getMilitaryPointsRanking().toString());
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	
}