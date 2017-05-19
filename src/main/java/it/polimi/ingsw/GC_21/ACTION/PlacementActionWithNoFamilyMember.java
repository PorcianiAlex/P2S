package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class PlacementActionWithNoFamilyMember {
	private final int actionValue;
	private final ActionSpace selectedActionSpace;
	
	public PlacementActionWithNoFamilyMember(int actionValue, ActionSpace selectedActionSpace) {
		super();
		this.actionValue = actionValue;
		this.selectedActionSpace = selectedActionSpace;
	}


	public int getActionValue() {
		return actionValue;
	}

}
