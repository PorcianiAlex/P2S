package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class TowerPlacement extends PlacementAction {
    
	private Floor floorSelected;
	private Tower towerSelected;
	
	
	@Override
	public void place() {
		
		super.place();
		floorSelected.getSingleActionSpace().setFamilyMember(super.getFamilyMemberSelected());
	}

	public boolean checkTakeabilityCard(int PersonalBoard, int Card) {
		return false;
	}

	/**
	 * 
	 * @param Card
	 * @param PersonalBoard
	 */
	public void checkCardRequirements(int Card, int PersonalBoard) {
		// TODO - implement TowerPlacement.checkCardRequirements
		throw new UnsupportedOperationException();
	}

	public void payPossesion() {
		// TODO - implement TowerPlacement.payPossesion
		throw new UnsupportedOperationException();
	}

	public void takeCard() {
		//super.getPlayerInAction().getMyPersonalBoard()
	}

}