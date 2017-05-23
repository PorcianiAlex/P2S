package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.CraftArea;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftPlacement extends PlacementAction {
	CraftType craftType;
	CraftArea craftArea;
	

	public CraftPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			CraftArea craftArea, Servants servantsToConvert, CraftType craftType, SingleActionSpace singleActionSpace) {
		super(playerInAction, actionValue, selectedFamilyMember, singleActionSpace, servantsToConvert);
		this.craftType = craftType;
	}
	
	public CraftPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			CraftArea craftArea, Servants servantsToConvert, CraftType craftType, MultipleActionSpace multipleActionSpace) {
		super(playerInAction, actionValue, selectedFamilyMember, multipleActionSpace, servantsToConvert);
		this.craftType = craftType;
	}

    public void callCraftEffect(CraftType craftType, ActionSpace actionSpace) {
		playerInAction.getMyPersonalBoard().checkCraftEffect(craftType, actionValue);
	}
    
    @Override
    public boolean checkPlaceRequirement() {
    	// TODO Auto-generated method stub
    	return super.checkPlaceRequirement() &&
    			craftArea.checkFamilyMemberColor(selectedFamilyMember.getColor());
    	
    }
    
    
}