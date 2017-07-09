package it.polimi.ingsw.GC_21.PLAYER;

import java.io.Serializable;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;

public class OwnedCards implements Serializable{
	private PersonalCardPlace[] myDevCards;
	private final DevCardType ownedDevCardsType;
	private final int DIMENSION;
	private int ownedDevCardsNumber;
	
	
	public void setOwnedCards(PersonalCardPlace[] ownedCards) {
		this.myDevCards = ownedCards;
	}

	public OwnedCards(DevCardType ownedCardsType, int dimension) {
		this.ownedDevCardsType = ownedCardsType;
		this.DIMENSION = dimension;
		this.myDevCards = new PersonalCardPlace[DIMENSION];
	}
	
	public static OwnedCards[] factoryOwnedCards() {
		OwnedCards[] ownedCards = new OwnedCards[4];
		ownedCards[0] = new OwnedCards(DevCardType.Venture, 6);
		ownedCards[0].initOwnedCards();
		ownedCards[1] = new OwnedCards(DevCardType.Building, 6);
		ownedCards[1].initOwnedCards();
		ownedCards[2] = new OwnedCards(DevCardType.Character, 6);
		ownedCards[2].initOwnedCards();
		ownedCards[3] = new OwnedCards(DevCardType.Territory, 6);
		ownedCards[3].initOwnedCards();
		return ownedCards;
	}
	
	public void initOwnedCards() {
		for (int i = 0; i < this.myDevCards.length; i++) {
			this.myDevCards[i]=new PersonalCardPlace(null, null);
		}
	}

	public void add(DevelopmentCard devCard){
		this.myDevCards[ownedDevCardsNumber].setCard(devCard);
		ownedDevCardsNumber++;
	}
	


	public PersonalCardPlace[] getMyDevCards() {
		return myDevCards;
	}


	public DevCardType getOwnedCardsType() {
		return ownedDevCardsType;
	}


	public int getOwnedCardsnumber() {
		return ownedDevCardsNumber;
	}


	public void setOwnedCardsnumber(int ownedCardsnumber) {
		this.ownedDevCardsNumber = ownedCardsnumber;
	}




	public int getDIMENSION() {
		return DIMENSION;
	}
	
	@Override
	public String toString() {
		String ownedCardString = ownedDevCardsType.toString() + ":[";
		for (int i = 0; i < ownedDevCardsNumber; i++) {
			ownedCardString = ownedCardString + myDevCards[i].getCard().toString() + " ";
		}
		return ownedCardString + "]";
	}
	


}
