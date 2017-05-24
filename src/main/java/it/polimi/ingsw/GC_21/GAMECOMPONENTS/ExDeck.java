package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Age;

public class ExDeck extends Deck {
	
	public ExDeck(int age) {
		cards = cardCreator.ExCardsCreate(age);
	}

	
}