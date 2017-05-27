package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Round {

	private int roundNumber;
	private Game game;
	private Turn currentTurn;
	
	public Round(int roundNumber, Game game) {
		this.roundNumber = roundNumber;
		this.game = game;
		this.placeCard();
		}
	
	public void executeRound() {
		for (int i = 1; i < game.getNumberOfPlayers(); i++) {
			currentTurn = new Turn(i, game);
			currentTurn.executeView();
		}
		
	}
	
	public void placeCard() {
		game.getBoard().getSpecificTower(DevCardType.Building).pickCards(game.getCurrentAge().buildingDeck);  
		game.getBoard().getSpecificTower(DevCardType.Territory).pickCards(game.getCurrentAge().territoryDeck);  
		game.getBoard().getSpecificTower(DevCardType.Venture).pickCards(game.getCurrentAge().ventureDeck);  
		game.getBoard().getSpecificTower(DevCardType.Character).pickCards(game.getCurrentAge().characterDeck);  
	
	}

	
}