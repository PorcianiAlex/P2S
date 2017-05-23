package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.OwnedCards;
import it.polimi.ingsw.GC_21.BOARD.ownedCards;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.EffectType;
import it.polimi.ingsw.GC_21.EFFECT.Permanent;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;

public class PersonalBoard {
	private final OwnedCards[] myOwnedCards;
	private ArrayList<Permanent> personalPermanetEffect;
	private final Possession craftMinimumReward;
	private Possession myPossession;


	
	public PersonalBoard() {
		this.myOwnedCards = new OwnedCards[4];
		this.personalPermanetEffect = new ArrayList<Permanent>();
		this.myPossession = new Possession(0, 0, 0, 0, 0, 0, 0);
		this.craftMinimumReward = new Possession(1,1,1,1,1,1,1);
	}
	
	public void addDevCard(DevelopmentCard devCard) {
		OwnedCards tmpCardType = getOwnedCards(devCard.getDevCardType());
		tmpCardType.add(devCard);
		}
	 

	public void payPossession(Possession possession){
		myPossession.subtract(possession);
	}
	
	public Effect checkEffect(String CardType) {
		// TODO - implement PersonalBoard.checkEffect
		throw new UnsupportedOperationException();
	}
	
	public void checkCraftEffect(CraftType craftType, int actionValue) {
		if(craftType.equals(CraftType.Production)) {
			OwnedCards ownedBuildingCardsCards = getOwnedCards(DevCardType.Building);
			for (int i = 0; i < ownedBuildingCardsCards.getOwnedCardsnumber(); i++) {
				CraftCard tmp = (CraftCard) ownedBuildingCardsCards.getMyOwnedCards()[i].getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
				tmp.callEffect(EffectType.Permanent);;
				}
			}
		} else if (craftType.equals(CraftType.Harvest)) {
			OwnedCards ownedTerritoryCards = getOwnedCards(DevCardType.Territory);
			for (int i = 0; i < ownedTerritoryCards.getOwnedCardsnumber(); i++) {
				CraftCard tmp = (CraftCard) ownedTerritoryCards.getMyOwnedCards()[i].getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
				tmp.callEffect(EffectType.Permanent);;
				}
			}
		}
	}
	
	public OwnedCards getOwnedCards(DevCardType devCardType){ //get a specific OwnedCards of the same type of the devCard 
		int i = 0;
		while (!myOwnedCards[i].getOwnedCardsType().equals(devCardType) && i < myOwnedCards.length){
			i++;
		}
		return myOwnedCards[i];	
	}

	public ArrayList<Permanent> getPersonalPermanetEffect() {
		return personalPermanetEffect;
	}

	public void setPersonalPermanetEffect(ArrayList<Permanent> personalPermanetEffect) {
		this.personalPermanetEffect = personalPermanetEffect;
	}

	public Possession getMyPossession() {
		return myPossession;
	}

	public void setMyPossession(Possession myPossession) {
		this.myPossession = myPossession;
	}

	@Override
	public String toString() {
		return "PersonalBoard [myPossession=" + myPossession.toString() + "]";
	}
	
	


}