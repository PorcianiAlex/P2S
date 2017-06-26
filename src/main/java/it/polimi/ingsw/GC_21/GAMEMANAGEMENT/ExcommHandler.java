package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.Arrays;

import it.polimi.ingsw.GC_21.CLIENT.ExcommMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ExDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ExcommunicationCard;

public class ExcommHandler implements Serializable{
	@Override
	public String toString() {
		return "ExcommHandler [excommunicationCards=" + Arrays.toString(excommunicationCards) + "]";
	}

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
		ExcommMessage excommMessage = new ExcommMessage();
		game.notifyBroadcast(excommMessage);
	}

	
	
}
