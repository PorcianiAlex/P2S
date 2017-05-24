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
	private final CraftType craftType;
	private final CraftArea craftArea;
	

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
	
	public static CraftPlacement factoryCraftPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			CraftArea craftArea, Servants servantsToConvert, CraftType craftType, MultipleActionSpace multipleActionSpace) {
		
		
	}

	@Override
    public boolean checkPlaceRequirement() {
    	return super.checkPlaceRequirement() &&
    			craftArea.checkFamilyMemberColor(selectedFamilyMember.getColor());
    	
    }
	
	@Override
	public void Execute() {	
		super.Execute();
		CraftAction craftAction = new CraftAction(playerInAction, craftType, actionValue);
	}
	
	
    
    
    
    
}