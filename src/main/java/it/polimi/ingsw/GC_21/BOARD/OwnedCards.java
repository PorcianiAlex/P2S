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


}
