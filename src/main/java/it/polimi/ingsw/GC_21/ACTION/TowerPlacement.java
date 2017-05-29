package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.OwnedCards;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Card;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Coins;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Ventures;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.PersonalCardPlace;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class TowerPlacement extends PlacementAction {
	private final Floor selectedFloor;
	private final Tower selectedTower;
	private final DevelopmentCard selectedCard;

	protected TowerPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			 Floor selectedFloor, Tower selectedTower, SingleActionSpace selectedActionSpace, Servants servantsToConvert, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board);
		this.selectedFloor = selectedFloor;
		this.selectedTower = selectedTower;
		this.selectedCard = (DevelopmentCard) selectedFloor.getDevCardPlace().getCard();
	}
	
	public static TowerPlacement factoryTowerPlacement(Player playerInAction, FamilyMemberColor selectedFamilyMemberColor, 
		    DevCardType towerType, int floorNumber, int servantsNumber, Board board){
		Tower selectedTower = board.getSpecificTower(towerType);
		Floor selectedFloor = selectedTower.getFloors()[floorNumber-1];
		SingleActionSpace selectedActionSpace = selectedFloor.getSingleActionSpace();
		FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(selectedFamilyMemberColor);
		int actionValue = selectedFamilyMember.getDiceAssociated().getValue();
		Servants servantsToConvert = new Servants(servantsNumber);
		TowerPlacement towerPlacement = new TowerPlacement(playerInAction, actionValue , selectedFamilyMember, selectedFloor, selectedTower, selectedActionSpace, servantsToConvert, board);
		return towerPlacement;
	}
	
	public static TowerPlacement factoryTowerPlacementWithNoFamilyMember(Player playerInAction, DevCardType towerType, int floorNumber, Board board) {
		return factoryTowerPlacement(playerInAction, null, towerType, floorNumber, 0, board);
		
	}
	
	
	
	@Override
	public boolean checkPlaceRequirement() {
		if (super.checkPlaceRequirement()) {
			if(selectedActionSpace.getActionSpaceEffect()!=null) {
			discount.add(selectedActionSpace.getActionSpaceEffect().getRewards());//if the Space requirement is satisfied I can use the eventual SpaceBonus to get the card
			}
		    if (checkBusyTower()) {
		    	Possession moneyToPay = new Possession(3, 0, 0, 0, 0, 0, 0);//needed addCoin to Possession
				overcharge.add(moneyToPay);
			}
			return checkTakeabilityCard(playerInAction.getMyPersonalBoard(), selectedCard.getDevCardType()) &&
					checkCardRequirements(playerInAction.getMyPersonalBoard());
		}
		return false;
				
	}
	
	@Override
	public void Execute() {
		 super.Execute();
		 pay(selectedCard.getRequirements());
		 takeCard();
	}
	
	@Override
	public boolean checkOtherFamilyMember() {
		if (selectedFamilyMember != null) {
			return selectedTower.checkFamilyMemberColorPresence(selectedFamilyMember.getColor());	
		}
		return false;
	}


	public boolean checkTakeabilityCard(PersonalBoard myPersonalBoard, DevCardType selectedCardType) {
		if(myPersonalBoard.getOwnedCards(selectedCardType).getOwnedCardsnumber() == 6){ 
			return false;
		    }
		OwnedCards ownedTerritoryCards = myPersonalBoard.getOwnedCards(DevCardType.Territory);
		if(selectedCardType.equals(DevCardType.Territory) 
		   && ownedTerritoryCards.getOwnedCardsnumber() > 2
		   && !myPersonalBoard.getMyPossession().compare(ownedTerritoryCards.getOwnedCards()[ownedTerritoryCards.getOwnedCardsnumber()].getPossession())){
		    // check on MilitaryPoint Required taking a territoryCard. The requirement is saved in the correct personalCardPlace with the attribute Possession 
			return false;
		}		  
		else
			return true;
	}

	
	public boolean checkCardRequirements(PersonalBoard myPersonalBoard) {
		Possession cost = selectedCard.getRequirements();
		cost.add(overcharge);
		cost.subtract(discount);
		return myPersonalBoard.getMyPossession().compare(cost);
	}
	
	public boolean checkBusyTower() {
		return selectedTower.checkFamilyMemberPresence();
	}

	public Floor getSelectedFloor() {
		return selectedFloor;
	}

	public Tower getSelectedTower() {
		return selectedTower;
	}

	public DevelopmentCard getSelectedCard() {
		return selectedCard;
	}

	public void pay(Possession payment) {
		payment.add(overcharge);
		payment.subtract(discount);//real payment
		playerInAction.getMyPersonalBoard().payPossession(payment);
	}

	public void takeCard() {
		playerInAction.getMyPersonalBoard().addDevCard(selectedCard);
		selectedFloor.getDevCardPlace().setCard(null);
	}

}