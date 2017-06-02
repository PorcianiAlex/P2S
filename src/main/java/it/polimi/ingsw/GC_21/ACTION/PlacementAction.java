package it.polimi.ingsw.GC_21.ACTION;

import org.omg.PortableServer.Servant;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementAction extends Action {

	protected int actionValue;
	protected final FamilyMember selectedFamilyMember;
	protected  ActionSpace selectedActionSpace;
	protected final Servants servantsToConvert;
	protected Possession discount;
	protected Possession overcharge;
	protected final Board board;
	
	public PlacementAction(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, ActionSpace selectedActionSpace, Servants servantsToConvert, Board board) {
		super(playerInAction);
		this.actionValue = actionValue;
		this.selectedFamilyMember = selectedFamilyMember;
		this.selectedActionSpace = selectedActionSpace;
		this.servantsToConvert = servantsToConvert;
		this.board = board;
		this.discount = new Possession();
		this.overcharge = new Possession();
	}
	
	@Override
	public void Execute() {
		convertServant(servantsToConvert);
	    place();
	    selectedActionSpace.callSpaceEffect(playerInAction, this);
		}
	
	@Override
	public boolean checkAction() {
		return checkPlaceRequirement();
	}
	
	public boolean checkPlaceRequirement(){
		System.out.println("Check Dice Requirement: " + checkDiceRequirement() + "/nFamily Member placed: " + selectedFamilyMember.isPlaced() + "/nAction Space Busy: " + selectedActionSpace.isBusy());
		return checkDiceRequirement() &&
			   !checkBusyActionSpace() &&
			   !checkBusyFamiliyMember() &&
			   !checkOtherFamilyMember();
			   
	}
	
	public boolean checkOtherFamilyMember() {
		return false;
	}


	public void convertServant(Servants servants) {
		this.actionValue += servants.getValue();
		playerInAction.getMyPersonalBoard().getMyPossession().subtractItemToPossession(servants);
		System.out.println("Action Value: " + actionValue);
	}
	
	public boolean checkBusyFamiliyMember() {
		return selectedFamilyMember.isPlaced();
	}
	public boolean checkBusyActionSpace() {
		return selectedActionSpace.isBusy();
	}

	public boolean checkDiceRequirement() {
		System.out.println("Required Dice: " + selectedActionSpace.getRequiredDice() + "Action value: " + actionValue + "Servants: " + servantsToConvert);
		return selectedActionSpace.getRequiredDice() <= (this.actionValue + servantsToConvert.getValue());
	}
	
	

	public void place() {
		if (selectedFamilyMember != null){
			selectedActionSpace.placeFamilyMember(selectedFamilyMember);
			selectedFamilyMember.setPlaced(true);
			
		}
		
		
		
	}

	public Possession getDiscount() {
		return discount;
	}

	public void setDiscount(Possession discount) {
		this.discount = discount;
	}

	public Possession getOvercharge() {
		return overcharge;
	}

	public void setOvercharge(Possession overcharge) {
		this.overcharge = overcharge;
	}

	public Servants getServantsToConvert() {
		return servantsToConvert;
	}

	public Board getBoard() {
		return board;
	}

	public void setSelectedActionSpace(ActionSpace selectedActionSpace) {
		this.selectedActionSpace = selectedActionSpace;
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