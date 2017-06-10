package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class LeaderDeck extends Deck {

	public LeaderDeck(Game game) {
		super(game);
		cards = cardCreator.LeadCardsCreate();
	}
	
}