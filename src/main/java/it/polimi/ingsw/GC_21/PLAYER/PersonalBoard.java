package it.polimi.ingsw.GC_21.PLAYER;

import java.lang.reflect.Array;
import java.util.*;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.OwnedCards;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.EffectType;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforeCraft;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforePlacement;
import it.polimi.ingsw.GC_21.EFFECT.ToCallDuringCraft;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;

public class PersonalBoard {
	private final OwnedCards[] myOwnedCards;
	private final Possession craftMinimumReward;
	private Possession myPossession;
	private Player player;
	private ArrayList<ToCallBeforeCraft> toCallBeforeCraftEffects;
	private ArrayList<ToCallBeforePlacement> toCallBeforePlacementEffects;
	
	public PersonalBoard(Player player) {
		this.myOwnedCards = OwnedCards.factoryOwnedCards();
		this.myPossession = new Possession(0, 0, 0, 0, 0, 0, 0);
		this.craftMinimumReward = new Possession(1,1,1,1,1,1,1);
		this.player = player;
		this.toCallBeforeCraftEffects= new ArrayList<ToCallBeforeCraft>();
		this.toCallBeforePlacementEffects = new ArrayList<ToCallBeforePlacement>();
	}
	
	public void addDevCard(DevelopmentCard devCard) {
		OwnedCards tmpCardType = getOwnedCards(devCard.getDevCardType());
		tmpCardType.add(devCard);
		}
	 

	public void addPermanentEffect(Effect effect){
		if (effect instanceof ToCallBeforeCraft){
			toCallBeforeCraftEffects.add((ToCallBeforeCraft) effect);
		}
		else if (effect instanceof ToCallBeforePlacement){
			toCallBeforePlacementEffects.add((ToCallBeforePlacement)effect);
		}
	}
	
	public void payPossession(Possession possession){
		if (possession!= null){
			myPossession.subtract(possession);
		}
	}

	public void activateCraft(CraftType craftType, int actionValue) {
		if(craftType.equals(CraftType.Production)) {
			OwnedCards ownedBuildingCardsCards = getOwnedCards(DevCardType.Building);
			for (int i = 0; i < ownedBuildingCardsCards.getOwnedCardsnumber(); i++) {
				CraftCard tmp = (CraftCard) ownedBuildingCardsCards.getMyOwnedCards()[i].getCard();
				System.out.println(tmp.toString());
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
					tmp.callCraftEffect(player);
				}
			}
		} else if (craftType.equals(CraftType.Harvest)) {
			OwnedCards ownedTerritoryCards = getOwnedCards(DevCardType.Territory);
			for (int i = 0; i < ownedTerritoryCards.getOwnedCardsnumber(); i++) {
				CraftCard tmp = (CraftCard) ownedTerritoryCards.getMyOwnedCards()[i].getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
					tmp.callCraftEffect(player);
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


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<ToCallBeforeCraft> getToCallBeforeCraftEffects() {
		return toCallBeforeCraftEffects;
	}

	public void setToCallBeforeCraftEffects(ArrayList<ToCallBeforeCraft> toCallBeforeCraftEffects) {
		this.toCallBeforeCraftEffects = toCallBeforeCraftEffects;
	}

	public ArrayList<ToCallBeforePlacement> getToCallBeforePlacementEffects() {
		return toCallBeforePlacementEffects;
	}

	public void setToCallBeforePlacementEffects(ArrayList<ToCallBeforePlacement> toCallBeforePlacementEffects) {
		this.toCallBeforePlacementEffects = toCallBeforePlacementEffects;
	}

	public OwnedCards[] getMyOwnedCards() {
		return myOwnedCards;
	}

	public Possession getCraftMinimumReward() {
		return craftMinimumReward;
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