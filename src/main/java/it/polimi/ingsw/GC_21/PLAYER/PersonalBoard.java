package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Permanent;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;

public class PersonalBoard {

	private ArrayList<PersonalCardPlace> buildings;
	private ArrayList<PersonalCardPlace> territories;
	private ArrayList<PersonalCardPlace> characters;
	private ArrayList<PersonalCardPlace> ventures;
	private ArrayList<Permanent> personalPermanetEffect;
	private Player player;
	private BonusTile  bonusTile; 


	
	public PersonalBoard(Player player, BonusTile bonusTile) {
		this.buildings = new ArrayList<PersonalCardPlace>();
		this.territories = new ArrayList<PersonalCardPlace>();
		this.characters = new ArrayList<PersonalCardPlace>();
		this.ventures = new ArrayList<PersonalCardPlace>();
		this.personalPermanetEffect = new ArrayList<Permanent>();
		this.player = player;
		this.bonusTile = bonusTile;
	}
	
	public void addCard(Card card) {
		
		
	}

	public Effect checkEffect(String CardType) {
		// TODO - implement PersonalBoard.checkEffect
		throw new UnsupportedOperationException();
	}
	
	public void callCraftEffect(CraftType craftType, int actionValue) {
		if(craftType.equals(CraftType.Production)) {
			for (int i = 0; i < buildings.size(); i++) {
				CraftCard tmp = (CraftCard) buildings.get(i).getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
				buildings.get(i).callEffect();
				}
			}
		} else if (craftType.equals(CraftType.Harvest)) {
			for (int i = 0; i < territories.size(); i++) {
				CraftCard tmp = (CraftCard) territories.get(i).getCard();
				if(actionValue >=  tmp.getRequiredValueForCraft()) {
				buildings.get(i).callEffect();
				}
			}
		}
	}

	public ArrayList<PersonalCardPlace> getBuildings() {
		return buildings;
	}

	public void setBuildings(ArrayList<PersonalCardPlace> buildings) {
		this.buildings = buildings;
	}

	public ArrayList<PersonalCardPlace> getTerritories() {
		return territories;
	}

	public void setTerritories(ArrayList<PersonalCardPlace> territories) {
		this.territories = territories;
	}

	public ArrayList<PersonalCardPlace> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<PersonalCardPlace> characters) {
		this.characters = characters;
	}

	public ArrayList<PersonalCardPlace> getVentures() {
		return ventures;
	}

	public void setVentures(ArrayList<PersonalCardPlace> ventures) {
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
	


}