package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class Deck {
	private Game game;
	protected ArrayList<Card> cards;
	protected CardType cardType;
    protected CardCreator cardCreator;
	
	
    
    public Deck(Game game) {
    	this.game=game;
    	cardCreator = new CardCreator(game);
    }

	public ArrayList<Card> getCards() {
		return cards;
	}
		
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card getSingleCard() {
		
		Card copyCard = cards.get(0);
		cards.remove(0);
		return copyCard;
	}
}