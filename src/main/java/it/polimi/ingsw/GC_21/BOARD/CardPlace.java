package it.polimi.ingsw.GC_21.BOARD;

import java.io.Serializable;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;

public class CardPlace implements Serializable {	
	private Card card;

	public CardPlace(Card card) {
		this.card = card;
	}

	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}


	@Override
	public String toString() {
		return "[card=" + card + "]";
	}
	
	
}