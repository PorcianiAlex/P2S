package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;
import java.util.Collection;

public class Deck {
	private ArrayList<Card> cards;
	private int deckIndex=0;

	public Deck(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public Card getSingleCard() {
	
		Card copyCard=cards.get(0);
		cards.remove(0);
		return copyCard;
	}
}