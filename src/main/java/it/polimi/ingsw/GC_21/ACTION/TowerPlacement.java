package it.polimi.ingsw.GC_21.ACTION;


import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.EffectType;
import it.polimi.ingsw.GC_21.EFFECT.Permanent;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Coins;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.OwnedCards;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
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
	
	protected TowerPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			 Floor selectedFloor, Tower selectedTower, SingleActionSpace selectedActionSpace, Servants servantsToConvert, Possession discount, Possession overcharge, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, discount, overcharge, servantsToConvert, board);
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
		int actionValue = selectedFamilyMember.getActionValue();
		Servants servantsToConvert = new Servants(servantsNumber);
		TowerPlacement towerPlacement = new TowerPlacement(playerInAction, actionValue , selectedFamilyMember, selectedFloor, selectedTower, selectedActionSpace, servantsToConvert, board);
		return towerPlacement;
	}
	
	public static TowerPlacement factoryTakeCard(Player playerInAction, DevCardType towerType, int floorNumber, int actionValue, Possession discount, Possession overcharge, Board board) {
		Tower selectedTower = board.getSpecificTower(towerType);
		Floor selectedFloor = selectedTower.getFloors()[floorNumber-1];
		SingleActionSpace selectedActionSpace = selectedFloor.getSingleActionSpace();
		TowerPlacement towerPlacement = new TowerPlacement(playerInAction, actionValue, null, selectedFloor, selectedTower, selectedActionSpace, new Servants(0), discount, overcharge, board);
		return towerPlacement;
	}
	
	@Override
	public boolean checkAction() {
		if (super.checkAction()) {
			if(selectedActionSpace.getActionSpaceEffect()!=null) {
				discount.add(selectedActionSpace.getActionSpaceEffect().getRewards());//if the Space requirement is satisfied I can use the eventual SpaceBonus to get the card
			}
		    if (checkBusyTower() && !playerInAction.isOverchargeOnBusyTower()) {
		    	Coins moneyToPay = new Coins(3);
				overcharge.addItemToPossession(moneyToPay);
			}
			return checkTakeabilityCard(playerInAction.getMyPersonalBoard(), selectedCard.getDevCardType()) &&
					checkCardRequirements(playerInAction.getMyPersonalBoard());
		}
		return false;
	}
	
	
	
	
	@Override
	public void Execute() {
		 super.Execute();
		 pay();
		 callCardEffect();
		 earnPermanentEffect(selectedCard);
		 takeCard();
	}
	
	public void earnPermanentEffect(DevelopmentCard selectedCard) {
		if (selectedCard != null) {
			Effect permanentEffectToGet = selectedCard.getSecondaryEffect();
			if (permanentEffectToGet!=null && permanentEffectToGet instanceof Permanent){
				playerInAction.getMyPersonalBoard().addPermanentEffect(permanentEffectToGet);
			}
		}
	}

	@Override
	public boolean checkOtherFamilyMember() {
		if (selectedFamilyMember != null && !selectedFamilyMember.getFamilyMemberColor().equals(FamilyMemberColor.Neutral)) {
			return selectedTower.checkTowerFamilyMemberOfMine(selectedFamilyMember.getOwnerPlayer());	
		}
		return false;
	}


	public boolean checkTakeabilityCard(PersonalBoard myPersonalBoard, DevCardType selectedCardType) {
		if (selectedCard!=null){
			if(myPersonalBoard.getSpecificOwnedCards(selectedCardType).getOwnedCardsnumber() == 6){ 
				return false;
		    }
			OwnedCards ownedTerritoryCards = myPersonalBoard.getSpecificOwnedCards(DevCardType.Territory);
			if(selectedCardType.equals(DevCardType.Territory) && !playerInAction.isCheckOnMP()
					&& ownedTerritoryCards.getOwnedCardsnumber() > 2
					&& !myPersonalBoard.getMyPossession().compare(ownedTerritoryCards.getMyDevCards()[ownedTerritoryCards.getOwnedCardsnumber()].getPossession())){
				// check on MilitaryPoint Required taking a territoryCard. The requirement is saved in the correct personalCardPlace with the attribute Possession 
				return false;
			}		  
			else
				return true;
			}
		return true;
	}

	
	public boolean checkCardRequirements(PersonalBoard myPersonalBoard) {
		if (selectedCard!=null){
			Possession cost = new Possession();
			cost.add(selectedCard.getRequirements());
			cost.add(overcharge);
			cost.subtract(discount);
			return myPersonalBoard.getMyPossession().compare(cost);
		}
		return true;
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

	
	public void pay() {
		if (selectedCard!=null){
			Possession payment = new Possession();
			payment.add(selectedCard.getRequirements());
			payment.add(overcharge);
			payment.subtract(discount);//real payment
			playerInAction.getMyPersonalBoard().payPossession(payment);
		}
	}

	public void callCardEffect(){
		if (selectedCard!=null){
			selectedCard.callEffect(EffectType.Immediate, playerInAction, this);
		}
	}
	
	public void takeCard() {
		if (selectedCard!=null){
			playerInAction.getMyPersonalBoard().addDevCard(selectedCard);
			selectedFloor.getDevCardPlace().setCard(null);
		}
	}
	
	@Override
	public String toString() {
		return "TOWER " + super.toString() + "\nSelected Floor=" + selectedFloor.toString() + "\nTaken Card " + selectedCard.toString() + "\nCard Requirement=" + 
				selectedCard.getRequirements().toString() + "\nCard Immediate Effect=" + selectedCard.getImmediateEffect().getRewards().toString() + "}";
				
				
	}
	
	@Override
	public String checkToString() {
		return super.checkToString() + "\nCheck Not Busy Tower=" + !checkBusyTower() + "\nCheck Card Takeability=" + 
				checkTakeabilityCard(playerInAction.getMyPersonalBoard(), selectedCard.getDevCardType()) +
				"\nCheck Card Requirement=" + checkCardRequirements(playerInAction.getMyPersonalBoard());
	}
	
	/*public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);
		Game game = new Game();	
		Board board = game.getBoard();
		Player playerInAction = new Player("Santa", Color.Blue, game);
		Player playerInAction2 = new Player("Alex", Color.Green, game);
		FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Neutral;
		FamilyMemberColor selectedFamilyMemberColor2 = FamilyMemberColor.Black;
		DevCardType towerType = DevCardType.Building;
		DevDeck towerTypeDeck  = new DevDeck(towerType, 1);
		board.getSpecificTower(towerType).pickCards(towerTypeDeck);
		System.out.println("Insert Floor");
		int floorNumber = tastiera.nextInt();
		System.out.println("Insert Servants");
		int servantsNumber = tastiera.nextInt();
		System.out.println("Insert Floor");
		int floorNumber2 = tastiera.nextInt();
		System.out.println("Insert Servants");
		int servantsNumber2 = tastiera.nextInt();
		TowerPlacement towerPlacement = factoryTowerPlacement(playerInAction, selectedFamilyMemberColor, towerType, floorNumber, servantsNumber, board);
		TowerPlacement towerPlacement2 = factoryTowerPlacement(playerInAction, selectedFamilyMemberColor2, towerType, floorNumber2, servantsNumber2, board);
		boolean checkAction = towerPlacement.checkAction();
		System.out.println("Before:\n" + towerPlacement.toString());
		System.out.println("Check Action [ " + checkAction + " ]");
		System.out.println(towerPlacement.checkToString());
		if (checkAction) {
			towerPlacement.Execute();
		}
		System.out.println("After:\n" + towerPlacement.toString());
		boolean checkAction2 = towerPlacement2.checkAction();
		System.out.println("Before:\n" + towerPlacement2.toString());
		System.out.println("Check Action [ " + checkAction + " ]");
		System.out.println(towerPlacement2.checkToString());
		if (checkAction2) {
			towerPlacement2.Execute();
		}
		System.out.println("After:\n" + towerPlacement2.toString());
		System.out.println(board.toString());

	}
	*/

}