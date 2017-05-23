package it.polimi.ingsw.GC_21.ACTION;

import org.omg.PortableServer.Servant;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementAction extends Action {

	protected int actionValue;
	protected final FamilyMember selectedFamilyMember;
	protected final ActionSpace selectedActionSpace;
	protected final Servants servantsToConvert;
	protected Possession discount;
	protected Possession overcharge;
	
	public PlacementAction(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, ActionSpace selectedActionSpace, Servants servantsToConvert) {
		super(playerInAction);
		this.actionValue = actionValue;
		this.selectedFamilyMember = selectedFamilyMember;
		this.selectedActionSpace = selectedActionSpace;
		this.servantsToConvert = servantsToConvert;
	}
	
	@Override
	public void Execute() {
		convertServant(servantsToConvert);
	    place();
	    selectedActionSpace.callSpaceEffect(playerInAction);
		}
	
	public boolean checkPlaceRequirement(){
		return checkDiceRequirement() &&
			!checkBusyActionSpace();
	}


	public void convertServant(Servants servants) {
		this.actionValue += servants.getValue();
	}
	
	public boolean checkBusyActionSpace() {
		return selectedActionSpace.isBusy();
		
	}

	public boolean checkDiceRequirement() {
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