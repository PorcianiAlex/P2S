package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class PlacementAction extends Action {

	private int actionValue;
	private FamilyMember familyMemberSelected;
	private ActionSpace selectedActionSpace;

	public void convertServant() {
		// TODO - implement PlacementAction.convertServant
		throw new UnsupportedOperationException();
	}
	
	public boolean checkPlaceRequirement() {
		return selectedActionSpace.getRequiredDice() <= this.actionValue;
	}

	public void place() {
		familyMemberSelected.setPlaced(true);
		
	}

	public int getActionValue() {
		return actionValue;
	}

	public void setActionValue(int actionValue) {
		this.actionValue = actionValue;
	}

	public FamilyMember getFamilyMemberSelected() {
		return familyMemberSelected;
	}

	public void setFamilyMemberSelected(FamilyMember familyMemberSelected) {
		this.familyMemberSelected = familyMemberSelected;
	}
	

}