package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.FamilyMemberColor;
import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.OwnedCards;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Ventures;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.PersonalCardPlace;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class TowerPlacement extends PlacementAction {
	private final Floor selectedFloor;
	private final Tower selectedTower;

	private TowerPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			 Floor selectedFloor, Tower selectedTower, Servants servantsToConvert, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedFloor.getSingleActionSpace(), servantsToConvert, board);
		this.selectedFloor = selectedFloor;
		this.selectedTower = selectedTower;
	}
	
	public static TowerPlacement factoryTowerPlacement(Player playerInAction, FamilyMemberColor selectedFamilyMemberColor, 
		    DevCardType towerType, int floorNumber, int servantsNumber, Board board){
		Tower selectedTower = board.getSpecificTower(towerType);
		Floor selectedFloor = selectedTower.getFloors()[floorNumber];
		SingleActionSpace selectedTowerActionSpace = selectedFloor.getSingleActionSpace();
		FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(selectedFamilyMemberColor);
		int actionValue = selectedFamilyMember.getDiceAssociated().getValue();
		Servants servantsToConvert = new Servants(servantsNumber);
		TowerPlacement towerPlacement = new TowerPlacement(playerInAction, actionValue , selectedFamilyMember, selectedFloor, selectedTower, servantsToConvert, board);
		return towerPlacement;
	}
	
	
	
	@Override
	public boolean checkPlaceRequirement() {
		DevelopmentCard floorDevCard = (DevelopmentCard)selectedFloor.getDevCardPlace().getCard();
		return super.checkPlaceRequirement() &&
				checkTakeabilityCard(playerInAction.getMyPersonalBoard(), floorDevCard.getDevCardType()) &&
				checkCardRequirements(floorDevCard, playerInAction.getMyPersonalBoard(), discount, overcharge);
		//TODO influences of effects
	}
	
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		 super.Execute();
		 selectedFloor.getSingleActionSpace().callIBonusEffect();
		 pay(selectedFloor.getDevCardPlace().getCard().getRequirements(), discount, overcharge);
		 //TODO influences of effects
	}


	public boolean checkTakeabilityCard(PersonalBoard myPersonalBoard, DevCardType selectedCardType) {
		if(myPersonalBoard.getOwnedCards(selectedCardType).getOwnedCardsnumber() == 6){ 
			return false;
		    }
		OwnedCards ownedTerritoryCards = myPersonalBoard.getOwnedCards(DevCardType.Territory);
		if(selectedCardType.equals(DevCardType.Territory) 
		   && myPersonalBoard.getOwnedCards(selectedCardType).getOwnedCardsnumber() > 2
		   && !myPersonalBoard.getMyPossession().compare(ownedTerritoryCards.getOwnedCards()[ownedTerritoryCards.getOwnedCardsnumber()].getPossession())){
		    // check on MilitaryPoint Required taking a territoryCard. The requirement is saved in the correct personalCardPlace with the attribute Possession 
			return false;
		}		  
		else
			return true;
	}

	
	public boolean checkCardRequirements(DevelopmentCard selectedCard, PersonalBoard myPersonalBoard, Possession discount, Possession overcharge) {
		Possession cost = selectedCard.getRequirements();
		cost.add(overcharge);
		cost.subtract(discount);
		return myPersonalBoard.getMyPossession().compare(cost);
	}
	
	public boolean checkBusyTower() {
		return selectedTower.checkFamilyMemberPresence();
	}

	public void pay(Possession payment, Possession discount, Possession overcharge) {
		payment.add(overcharge);
		payment.subtract(discount);//real payment
		playerInAction.getMyPersonalBoard().payPossession(payment);
	}

	public void takeCard() {
		DevelopmentCard takenCard = (DevelopmentCard) selectedFloor.getDevCardPlace().getCard();
		playerInAction.getMyPersonalBoard().addDevCard(takenCard); 
	}

}