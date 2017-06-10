package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Age;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class ExDeck extends Deck {
	
	public ExDeck(Game game, int age) {
		super(game);
		cards = cardCreator.ExCardsCreate(age);
	}

	
}