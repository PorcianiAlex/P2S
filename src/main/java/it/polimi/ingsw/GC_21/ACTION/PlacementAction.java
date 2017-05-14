package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class PlacementAction extends Action {

	private int actionValue;
	private FamilyMember familyMemberSelected;

	public void convertServant() {
		// TODO - implement PlacementAction.convertServant
		throw new UnsupportedOperationException();
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