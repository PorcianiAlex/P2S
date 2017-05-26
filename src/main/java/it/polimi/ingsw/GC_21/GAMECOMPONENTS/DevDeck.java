package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;
import java.util.Collection;

public class DevDeck extends Deck {
	private DevCardType devCardType;
	private int age;
	
	public DevDeck(DevCardType devCardType, int age) {
		super();
		this.devCardType = devCardType;
		this.age = age;
		cards = cardCreator.devCardsCreate(devCardType, age);
	}

		
}