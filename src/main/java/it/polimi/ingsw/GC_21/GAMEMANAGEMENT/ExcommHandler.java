package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ExDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ExcommunicationCard;
import it.polimi.ingsw.GC_21.view.ExcommInput;

public class ExcommHandler {
	private ExcommunicationCard[] excommunicationCards;
	private int[] excommThresholds;
	private Game game;
	
	public ExcommHandler(Game game) {
		excommunicationCards = new ExcommunicationCard[3];
		excommThresholds = new int[3];
		for (int i = 1; i <= 3; i++) {
			ExDeck exDeck = new ExDeck(game, i);
			exDeck.shuffle();
		    excommunicationCards[i-1] = (ExcommunicationCard) exDeck.getCards().get(0);
		}
		excommThresholds[0]=3;
		excommThresholds[1]=4;
		excommThresholds[2]=5;
		this.game=game;
	}
	
	public ExcommunicationCard[] getExcommunicationCards() {
		return excommunicationCards;
	}

	public void setExcommunicationCards(ExcommunicationCard[] excommunicationCards) {
		this.excommunicationCards = excommunicationCards;
	}

	public int[] getExcommThresholds() {
		return excommThresholds;
	}

	public void setExcommThresholds(int[] excommThresholds) {
		this.excommThresholds = excommThresholds;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void executeExcomm() {
		ExcommInput excommInput = new ExcommInput();
		game.notifyCurrent(excommInput);;
	}

	
	
}
