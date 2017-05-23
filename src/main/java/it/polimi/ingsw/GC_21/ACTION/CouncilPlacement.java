package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.CouncilPalace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPlacement extends PlacementAction {

	public CouncilPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			CouncilPalace councilPalace, Servants servantsToConvert) {
		super(playerInAction, actionValue, selectedFamilyMember, councilPalace.getMultipleActionSpace(), servantsToConvert);
	}
}