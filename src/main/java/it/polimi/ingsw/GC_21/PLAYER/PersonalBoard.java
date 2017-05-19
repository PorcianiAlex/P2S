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
		int i = 0;
		while (!myOwnedCards[i].getOwnedCardsType().equals(devCard.getDevCardType()) && i < myOwnedCards.length)
				i++;
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

	public PersonalCardPlace[] getBuildings() {
		return buildings;
	}

	public void setBuildings(PersonalCardPlace[] buildings) {
		this.buildings = buildings;
	}

	public PersonalCardPlace[] getTerritories() {
		return territories;
	}

	public void setTerritories(PersonalCardPlace[] territories) {
		this.territories = territories;
	}

	public PersonalCardPlace[] getCharacters() {
		return characters;
	}

	public void setCharacters(PersonalCardPlace[] characters) {
		this.characters = characters;
	}

	public PersonalCardPlace[] getVentures() {
		return ventures;
	}

	public void setVentures(PersonalCardPlace[] ventures) {
		this.ventures = ventures;
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

	public void setPlayer(Player player) {
		this.player = player;
	}

	public BonusTile getBonusTile() {
		return bonusTile;
	}

	public void setBonusTile(BonusTile bonusTile) {
		this.bonusTile = bonusTile;
	}
	
	public PersonalCardPlace[] getArrayCardType(DevCardType devCardType){ //get an Array with the same CardTypeof the given one 
		if (devCardType.equals(DevCardType.Building)){
			return buildings;
		}
		if (devCardType.equals(DevCardType.Character)){
			return characters;
		}
		if (devCardType.equals(DevCardType.Venture)){
			return ventures;
		}
		else {
			return territories;
		}
		
	}

	public Possession getMyPossession() {
		return myPossession;
	}

	public void setMyPossession(Possession myPossession) {
		this.myPossession = myPossession;
	}
	
	


}