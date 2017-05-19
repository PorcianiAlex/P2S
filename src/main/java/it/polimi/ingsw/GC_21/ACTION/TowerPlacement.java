package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Ventures;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class TowerPlacement extends PlacementAction {
    
	private Floor selectedFloor;
	private Tower selectedTower;
	
	
	

	public boolean checkTakeabilityCard(PersonalBoard myPersonalBoard, DevCardType selectedCardType) {
		if(myPersonalBoard.getOwnedCards(selectedCardType).getOwnedCardsnumber() == 6){ 
			return false;
		    }
		if(selectedCardType.equals(DevCardType.Territory) 
		   && myPersonalBoard.getOwnedCards(selectedCardType).getOwnedCardsnumber() > 2
		   && !myPersonalBoard.getMyPossession().compare(myPersonalBoard.getOwnedCards(DevCardType.Territory)[myPersonalBoard.getOwnedCards(DevCardType.Territory).getOwnedCardsnumber()].getPossession())){
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
		return selectedTower.checkFamilyMemberPresence();
	}

	public void payPossesion() {
		// TODO - implement TowerPlacement.payPossesion
		throw new UnsupportedOperationException();
	}

	public void takeCard() {
		Card cardToken = selectedFloor.getDevCardPlace().getCard();
		super.getPlayerInAction().getMyPersonalBoard(). 
	}

}