package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.MarketArea;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class MarketPlacement extends PlacementAction {
 

	public MarketPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, MarketArea marketArea,
			int position,  Servants servantsToConvert) {
		super(playerInAction, actionValue, selectedFamilyMember, marketArea.getSingleActionSpace()[position], servantsToConvert); 
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean checkPlaceRequirement() {
		SingleActionSpace cell = (SingleActionSpace) selectedActionSpace;
		return super.checkPlaceRequirement() &&
			   !cell.isBusy();
	}
}