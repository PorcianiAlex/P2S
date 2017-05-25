package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.CouncilPalace;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPlacement extends PlacementAction {

	private CouncilPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			MultipleActionSpace selectedActionSpace, Servants servantsToConvert, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace , servantsToConvert, board);
	}
	
	public static CouncilPlacement factoryCouncilPlacement(Player playerInAction, FamilyMemberColor familyMemberColor,
			Board board, int servantsNumber) {
	        CouncilPalace selectedCouncilPalace = board.getCouncilPalace();
	        FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(familyMemberColor);
	        int actionValue = selectedFamilyMember.getDiceAssociated().getValue();
	        Servants servantsToConvert = new Servants(servantsNumber);
	        MultipleActionSpace selectedActionSpace = board.getCouncilPalace().getMultipleActionSpace();
	        CouncilPlacement councilPlacement = new CouncilPlacement(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board);
	        return councilPlacement;
	}
}