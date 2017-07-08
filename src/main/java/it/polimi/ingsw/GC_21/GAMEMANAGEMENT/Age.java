package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;

public class Age implements Serializable{
	private int ageNumber;
 	DevDeck buildingDeck ;
	DevDeck territoryDeck;
	DevDeck characterDeck;
	DevDeck ventureDeck;
	private Game game;
	private ExcommHandler excommHandler;
	
	
	public Age(int ageNumber, Game game) {
		this.ageNumber = ageNumber;
		this.game = game;
	}
	
	public void executeAge() {
		this.excommHandler = game.getExcommHandler();
		this.CreateDeck();	
		for (int i = game.getCurrentRound().getRoundNumber(); i < 3; i++) {
			Round currentRound = new Round(i, game);
			game.setCurrentRound(currentRound);
			currentRound.executeRound();
		}
		excommHandler.executeExcomm();
	}

	public void CreateDeck() {
		buildingDeck = new DevDeck(game, DevCardType.Building, ageNumber);
		territoryDeck = new DevDeck(game, DevCardType.Territory, ageNumber);
		characterDeck = new DevDeck(game, DevCardType.Character, ageNumber);
		ventureDeck = new DevDeck(game, DevCardType.Venture, ageNumber);
		buildingDeck.shuffle();
		territoryDeck.shuffle();
		characterDeck.shuffle();
		ventureDeck.shuffle();
	}

	

	public int getAgeNumber() {
		return ageNumber;
	}

	public void setAgeNumber(int ageNumber) {
		this.ageNumber = ageNumber;
	}

	
}