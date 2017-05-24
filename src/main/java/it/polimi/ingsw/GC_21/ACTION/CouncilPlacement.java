package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.CouncilPalace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPlacement extends PlacementAction {

	private CouncilPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			CouncilPalace councilPalace, Servants servantsToConvert, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, councilPalace.getMultipleActionSpace(), servantsToConvert, board);
	}
	
	public static CouncilPlacement factoryCouncilPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			Board board, Servants servantsToConvert) {
	        CouncilPalace selectedCouncilPalace = board.getCouncilPalace();
	        CouncilPlacement councilPlacement = new CouncilPlacement(playerInAction, actionValue, selectedFamilyMember, selectedCouncilPalace, servantsToConvert, board);
	        return councilPlacement;
	}
}