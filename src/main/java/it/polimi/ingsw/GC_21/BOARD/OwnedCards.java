package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.CraftCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.PLAYER.PersonalCardPlace;

public class OwnedCards {
	private final PersonalCardPlace[] ownedCards;
	private final DevCardType ownedCardsType;
	private final int DIMENSION;
	private int ownedCardsnumber;
	
	
	public OwnedCards(DevCardType ownedCardsType, int dimension) {
		this.ownedCardsType = ownedCardsType;
		this.DIMENSION = dimension;
		this.ownedCards = new PersonalCardPlace[DIMENSION];
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
		for (int i = 0; i < this.ownedCards.length; i++) {
			this.ownedCards[i]=new PersonalCardPlace(null, null);
		}
	}

	public void add(DevelopmentCard devCard){
		this.ownedCards[ownedCardsnumber].setCard(devCard);
		ownedCardsnumber++;
	}
	


	public PersonalCardPlace[] getMyOwnedCards() {
		return ownedCards;
	}


	public DevCardType getOwnedCardsType() {
		return ownedCardsType;
	}


	public int getOwnedCardsnumber() {
		return ownedCardsnumber;
	}


	public void setOwnedCardsnumber(int ownedCardsnumber) {
		this.ownedCardsnumber = ownedCardsnumber;
	}


	public PersonalCardPlace[] getOwnedCards() {
		return ownedCards;
	}


	public int getDIMENSION() {
		return DIMENSION;
	}
	
	@Override
	public String toString() {
		String ownedCardString = "";
		for (int i = 0; i < ownedCardsnumber; i++) {
			ownedCardString = ownedCardString + ownedCards[i].getCard().toString() + " ";
		}
		return ownedCardString;
	}
	


}
