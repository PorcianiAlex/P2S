package it.polimi.ingsw.GC_21.ACTION;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class MarketPlacement extends PlacementAction {
 

	private MarketPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,  Servants servantsToConvert, SingleActionSpace selectedActionSpace, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board); 
		
	}
	
	public static MarketPlacement factoryMarketPlacement(Player playerInAction, FamilyMemberColor familyMemberColor, 
			int position,  int servantsNumber, Board board) {
		FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(familyMemberColor);
		Servants servantsToConvert = new Servants(servantsNumber);
		int actionValue = selectedFamilyMember.getActionValue();
		SingleActionSpace selectedActionSpace = board.getMarketArea().getSingleActionSpace()[position-1];
		MarketPlacement marketPlacement = new MarketPlacement(playerInAction, actionValue, selectedFamilyMember, servantsToConvert, selectedActionSpace, board);
		return marketPlacement;
	}
	

	
	

}