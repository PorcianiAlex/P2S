package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;
import java.util.Collection;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class DevDeck extends Deck {
	private DevCardType devCardType;
	private int age;
	
	public DevDeck(Game game, DevCardType devCardType, int age) {
		super(game);
		this.devCardType = devCardType;
		this.age = age;
		cards = cardCreator.devCardsCreate(devCardType, age);
	}

		
}