package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import it.polimi.ingsw.GC_21.BOARD.CardPlace;
import it.polimi.ingsw.GC_21.BOARD.DevCardPlace;

public class DevelopmentCard extends Card {
	
	private int age;
	private DevCardType devCardType;
	private CardPlace cardPlace;
	
	
	public DevelopmentCard(String name) {
		super(name);
	
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public DevCardType getDevCardType() {
		return devCardType;
	}


	public void setDevCardType(DevCardType devCardType) {
		this.devCardType = devCardType;
	}


	public CardPlace getCardPlace() {
		return cardPlace;
	}


	public void setCardPlace(CardPlace cardPlace) {
		this.cardPlace = cardPlace;
	}
	


}