package it.polimi.ingsw.GC_21.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;

public class Board {

	private Dice[] dices = new Dice[3];
	private Tower[] towers = new Tower[4];
	private MarketArea marketArea;
	private CouncilPalace councilPalace;
	private CraftArea[] craftAreas=new CraftArea[2];
	
	public void refreshBoard() {
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
		
		
	
	}

	
}