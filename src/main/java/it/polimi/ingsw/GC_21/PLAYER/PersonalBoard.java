package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.OwnedCards;
import it.polimi.ingsw.GC_21.BOARD.ownedCards;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Permanent;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;

public class PersonalBoard {
	private final OwnedCards[] myOwnedCards;
	private ArrayList<Permanent> personalPermanetEffect;
	private final Player player;
	private BonusTile  bonusTile;
	private Possession myPossession;


	
	public PersonalBoard(Player player, BonusTile bonusTile) {
		this.myOwnedCards = new OwnedCards[4];
		this.personalPermanetEffect = new ArrayList<Permanent>();
		this.player = player;
		this.bonusTile = bonusTile;
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
	
	public void callCraftEffect(CraftType craftType, int actionValue) {
		if(craftType.equals(CraftType.Production)) {
			for (int i = 0; i < buildings.length; i++) {
				CraftCard tmp = (CraftCard) buildings[i].getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
				buildings[i].callEffect();
				}
			}
		} else if (craftType.equals(CraftType.Harvest)) {
			for (int i = 0; i < territories.length; i++) {
				CraftCard tmp = (CraftCard) territories[i].getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
				buildings[i].callEffect();
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

	public Player getPlayer() {
		return player;
	}


	public BonusTile getBonusTile() {
		return bonusTile;
	}

	public void setBonusTile(BonusTile bonusTile) {
		this.bonusTile = bonusTile;
	}
	

	public Possession getMyPossession() {
		return myPossession;
	}

	public void setMyPossession(Possession myPossession) {
		this.myPossession = myPossession;
	}
	
	


}