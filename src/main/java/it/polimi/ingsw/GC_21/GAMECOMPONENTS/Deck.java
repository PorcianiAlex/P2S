package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.UTILITIES.CardCreator;

public class Deck implements Serializable{
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