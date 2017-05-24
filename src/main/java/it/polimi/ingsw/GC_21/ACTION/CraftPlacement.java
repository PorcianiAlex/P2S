package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.CraftArea;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.FamilyMemberColor;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftPlacement extends PlacementAction {
	private final CraftType craftType;
	private final CraftArea craftArea;
	

	public CraftPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, Servants servantsToConvert, CraftType craftType, ActionSpace selectedActionSpace, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board);
		this.craftType = craftType;
		this.craftArea = board.getHarvestArea();
	}
	//TODO to correct
	public static CraftPlacement factoryCraftPlacement(Player playerInAction, FamilyMemberColor familyMemberColor,
			Board board, int servantsNumber, CraftType craftType, String spaceType) {
		FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(familyMemberColor);
		int actionValue = selectedFamilyMember.getDiceAssociated().getValue();
		Servants servantsToConvert = new Servants(servantsNumber);
		ActionSpace selectedActionSpace = board.getHarvestArea().getMultipleActionSpace();
		CraftPlacement craftPlacement = new CraftPlacement(playerInAction, actionValue, selectedFamilyMember, servantsToConvert, craftType, selectedActionSpace, board);
		return craftPlacement;
		
		
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