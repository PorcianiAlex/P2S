package it.polimi.ingsw.GC_21.BOARD;

import java.util.ArrayList;
import java.util.Arrays;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class Board {

	private Dice[] dices;
	private Tower[] towers;
	private MarketArea marketArea;
	private CouncilPalace councilPalace;
	private CraftArea productionArea;
	private CraftArea harvestArea;
	
	public Board() {
		super();
		this.dices = Dice.factoryDices();
		this.towers = Tower.factoryTowers(); 
		this.marketArea = new MarketArea();
		this.councilPalace = new CouncilPalace();
		/*
		 CONSTRUCT CRAFT AREAS
		 */
	}



	public void generateBoard(DevDeck territoryDeck, DevDeck buildingDeck, DevDeck characterDeck, DevDeck ventureDeck){
		dices = Dice.factoryDices();
		towers[0].pickCards(territoryDeck);
		towers[1].pickCards(buildingDeck);
		towers[2].pickCards(characterDeck);
		towers[3].pickCards(ventureDeck);
	}



	/*public void refreshBoard() {
		for (int i = 0; i < towers.length; i++) {
			for (int j = 0; j < towers[i].getFloors().length; j++) {
				towers[i].getFloors()[j].getSingleActionSpace().setFamilyMember(null);;
			}
		}
		for (int i = 0; i < towers.length; i++) {
			for (int j = 0; j < towers[i].getFloors().length; j++) {
				towers[i].getFloors()[j].getDevCardPlace().setCard(null);
			}
		}	
		for (int j = 0; j < craftAreas.length; j++) {
			for (int i = 0; i < craftAreas[i].getMultipleActionSpace().getFamilyMember().size(); i++) {
				craftAreas[i].getMultipleActionSpace().getFamilyMember().remove(i);		

			}
			craftAreas[j].getSingleActionSpace().setFamilyMember(null);
		}
		for (int i = 0; i < councilPalace.getMultipleActionSpace().getFamilyMember().size(); i++) {
			councilPalace.getMultipleActionSpace().getFamilyMember().remove(i);
			
		}
		for (int i = 0; i < marketArea.getSingleActionSpace().length; i++) {
			marketArea.getSingleActionSpace()[i].setFamilyMember(null);
		}
		
		for (int j = 0; j < dices.length; j++) {
			//<todo>
		}
		
	
	}*/
	
	

	public Dice[] getDices() {
		return dices;
	}



	public void setDices(Dice[] dices) {
		this.dices = dices;
	}



	public Tower[] getTowers() {
		return towers;
	}
	
	public Tower getSpecificTower(DevCardType towerType) {
		for (int i = 0; i < towers.length; i++) {
			if (towers[i].getDevCardType().equals(towerType)) {
				return towers[i];
			}
		}
		return null;
	}



	public void setTowers(Tower[] towers) {
		this.towers = towers;
	}



	public MarketArea getMarketArea() {
		return marketArea;
	}



	public void setMarketArea(MarketArea marketArea) {
		this.marketArea = marketArea;
	}



	public CouncilPalace getCouncilPalace() {
		return councilPalace;
	}



	public void setCouncilPalace(CouncilPalace councilPalace) {
		this.councilPalace = councilPalace;
	}



	public CraftArea getProductionArea() {
		return productionArea;
	}



	public void setProductionArea(CraftArea productionArea) {
		this.productionArea = productionArea;
	}



	public CraftArea getHarvestArea() {
		return harvestArea;
	}



	public void setHarvestArea(CraftArea harvestArea) {
		this.harvestArea = harvestArea;
	}



	public void printBoard(){
		System.out.println(dices[0].toString());
		System.out.println(dices[1].toString());
		System.out.println(dices[2].toString());
		System.out.println(towers[0].toString());
		System.out.println(towers[1].toString());
		System.out.println(towers[2].toString());
		System.out.println(towers[3].toString());
		System.out.println(marketArea.toString());
		System.out.println(councilPalace.toString());
	}



	public static void main(String[] args) {
		Board aaa = new Board();
		aaa.printBoard();
		
	}

	
}