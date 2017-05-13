package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.ArrayList;
import java.util.Collection;

public class DevDeck extends Deck {
	private DevCardType devCardType;
	private int age;
	
	public DevDeck(ArrayList<Card> cards, DevCardType devCardType, int age) {
		super(cards);
		this.devCardType = devCardType;
		this.age = age;
	}

	public DevCardType getDevCardType() {
		return devCardType;
	}

	public void setDevCardType(DevCardType devCardType) {
		this.devCardType = devCardType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}