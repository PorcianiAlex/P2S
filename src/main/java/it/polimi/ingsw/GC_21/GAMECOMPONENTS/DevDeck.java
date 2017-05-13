package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.Collection;

public class DevDeck extends Deck {
	private DevCardType devCardType;
	private int age;
	
	public DevDeck(Collection<Card> cards, DevCardType devCardType, int age) {
		super(cards);
		this.devCardType = devCardType;
		this.age = age;
	}
}