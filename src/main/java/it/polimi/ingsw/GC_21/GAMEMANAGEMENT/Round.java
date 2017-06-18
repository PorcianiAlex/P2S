package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Round implements Serializable{

	private int roundNumber;
	private Game game;
	private Turn currentTurn;
	
	public Round(int roundNumber, Game game) {
		this.roundNumber = roundNumber;
		this.game = game;
		game.getBoard().refreshBoard();
		for (int i = 0; i < game.getPlayers().size(); i++) {
			game.getPlayers().get(i).refreshPlayer();
		}
		this.placeCard();
		}
	
	public void executeRound() {
		for (int i = 1; i < 5 ; i++) {
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

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(Turn currentTurn) {
		this.currentTurn = currentTurn;
	}

	
	
}