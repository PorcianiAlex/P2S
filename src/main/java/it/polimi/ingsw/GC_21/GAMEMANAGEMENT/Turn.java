package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.util.ArrayList;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.PLAYER.Player;
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
			System.out.println(game.getPlayers().size());
			
			game.notifyObservers("Age: "+ game.getCurrentAge().getAgeNumber() + "\nRound: " + game.getCurrentAge().getCurrentRound().getRoundNumber() 
					+"\nTurn: "+turnNumber +"\nTurn of player :"+ game.getPlayers().get(i).getName() +" color: " +game.getPlayers().get(i).getPlayerColor().toString()
					+"\n Your resources: " + game.getPlayers().get(i).getMyPersonalBoard().getMyPossession().toString() + 
					"\n" );
			game.notifyObservers(game.getPlayers().get(i).getMyView());		
			System.out.println("\n \n \n");
		}
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	
}