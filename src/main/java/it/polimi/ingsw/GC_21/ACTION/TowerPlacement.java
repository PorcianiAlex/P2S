package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Ventures;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class TowerPlacement extends PlacementAction {
    
	private Floor selectedFloor;
	private Tower selectedTower;
	
	
	@Override
	public void place() {
		super.place();
		selectedFloor.getSingleActionSpace().setFamilyMember(super.getFamilyMemberSelected());
	}

	public boolean checkTakeabilityCard(PersonalBoard myPersonalBoard, DevelopmentCard selectedCard) {
		DevCardType tmpCardType = selectedCard.getDevCardType();
		if(myPersonalBoard.getArrayCardType(selectedCard.getDevCardType()).size() == 6){
			return false;
		    }
		if(selectedCard.getDevCardType().equals(DevCardType.Territory) 
		   && myPersonalBoard.getArrayCardType(selectedCard.getDevCardType()).size() > 2
		   && !myPersonalBoard.getMyPossession().compare(myPersonalBoard.getArrayCardType(DevCardType.Territory).get(myPersonalBoard.getArrayCardType(DevCardType.Territory).size()).getPossession())){
		    // check on MilitaryPoint Required taking a territoryCard. The requirement is saved in the correct cardPlace with the attribute Possession 
			return false;
		}		  
		else
			return true;
	}

	
	public boolean checkCardRequirements(DevelopmentCard selectedCard, PersonalBoard myPersonalBoard) {
		
		return myPersonalBoard.getMyPossession().compare(selectedCard.getRequirements());
	}
	
	public boolean checkBusyTower() {
		// TODO while cycle checking business of tower
	}

	public void payPossesion() {
		// TODO - implement TowerPlacement.payPossesion
		throw new UnsupportedOperationException();
	}

	public void takeCard() {
		//super.getPlayerInAction().getMyPersonalBoard()
	}

}