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
	private Tower selectedtower;
	
	
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
		if(selectedCard.getDevCardType().equals(DevCardType.Territory) && myPersonalBoard.getArrayCardType(selectedCard.getDevCardType()).size() > 2){
		}		  
		else
			return true;
	}

	
	public boolean checkCardRequirements(DevelopmentCard selectedCard, PersonalBoard myPersonalBoard) {
		
		if (selectedCard.getDevCardType().equals(DevCardType.Venture)){
			
			//TODO check secondary requirement 
		}
		return myPersonalBoard.getMyPossession().compare(selectedCard.getRequirements());
	}

	public void payPossesion() {
		// TODO - implement TowerPlacement.payPossesion
		throw new UnsupportedOperationException();
	}

	public void takeCard() {
		//super.getPlayerInAction().getMyPersonalBoard()
	}

}