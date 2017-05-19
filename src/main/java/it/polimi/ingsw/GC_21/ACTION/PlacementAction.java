package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementAction extends Action {

	protected int actionValue;
	protected final FamilyMember selectedFamilyMember;
	protected final ActionSpace selectedActionSpace;
	
	public PlacementAction(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, ActionSpace selectedActionSpace) {
		super(playerInAction);
		this.actionValue = actionValue;
		this.selectedFamilyMember = selectedFamilyMember;
		this.selectedActionSpace = selectedActionSpace;
	}
	
	@Override
	public boolean Execute() {
		convertServant();
		if (checkPlaceRequirement()){
			place();
			return true;
		}
		return false;
	}

	public void convertServant() {
		// TODO - implement PlacementAction.convertServant
		throw new UnsupportedOperationException();
	}
	
	

	public boolean checkPlaceRequirement() {
		return selectedActionSpace.getRequiredDice() <= this.actionValue;
	}
	
	

	public void place() {
		selectedFamilyMember.setPlaced(true);
		selectedActionSpace.placeFamilyMember(selectedFamilyMember);
		
	}

	public int getActionValue() {
		return actionValue;
	}
	
	public void setActionValue(int actionValue) {
		this.actionValue = actionValue;
	}


	public FamilyMember getSelectedFamilyMember() {
		return selectedFamilyMember;
	}

	public ActionSpace getSelectedActionSpace() {
		return selectedActionSpace;
	}
	

}