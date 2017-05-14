package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.CraftCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;

public class CardPlace {	
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
	
	
	
}