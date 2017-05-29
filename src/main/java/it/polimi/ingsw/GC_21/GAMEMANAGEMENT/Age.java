package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Age {

	private int ageNumber;
	private int excommTreshold;
	DevDeck buildingDeck ;
	DevDeck territoryDeck;
	DevDeck characterDeck;
	DevDeck ventureDeck;
	private Game game;
	private Round currentRound;
	
	
	public Age(int ageNumber, Game game) {
		this.ageNumber = ageNumber;
		this.excommTreshold = ageNumber+2;
		this.game = game;
		this.CreateDeck();			
	}
	
	public void executeAge() {
		for (int i = 1; i < 3; i++) {
			currentRound = new Round(i, game);
			currentRound.executeRound();
		}
		
	}

	public void CreateDeck() {
		buildingDeck = new DevDeck(DevCardType.Building, ageNumber);
		territoryDeck = new DevDeck(DevCardType.Territory, ageNumber);
		characterDeck = new DevDeck(DevCardType.Character, ageNumber);
		ventureDeck = new DevDeck(DevCardType.Venture, ageNumber);
		buildingDeck.shuffle();
		territoryDeck.shuffle();
		characterDeck.shuffle();
		ventureDeck.shuffle();
	}

	public void checkExcomm() {
		// TODO - implement Age.checkExcomm
		throw new UnsupportedOperationException();
	}

	public void askExcomm() {
		// TODO - implement Age.askExcomm
		throw new UnsupportedOperationException();
	}

	public void callImmediateEffect() {
		// TODO - implement Age.callImmediateEffect
		throw new UnsupportedOperationException();
	}



	public Round getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(Round currentRound) {
		this.currentRound = currentRound;
	}

	public int getAgeNumber() {
		return ageNumber;
	}

	public void setAgeNumber(int ageNumber) {
		this.ageNumber = ageNumber;
	}

	
}