package it.polimi.ingsw.GC_21.ACTION;

import java.util.ArrayList;
import java.util.Scanner;

import javax.management.InstanceAlreadyExistsException;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftArea;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforeCraft;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftPlacement extends PlacementAction {
	private final CraftArea craftArea;
	

	public CraftPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember, Servants servantsToConvert, CraftArea craftArea, ActionSpace selectedActionSpace, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board);
		this.craftArea = craftArea;
	}
	
	public static CraftPlacement factoryCraftPlacement(Player playerInAction, FamilyMemberColor familyMemberColor,
			Board board, int servantsNumber, CraftType craftType, int spaceType) {
		FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(familyMemberColor);
		int actionValue = selectedFamilyMember.getAssociatedDice().getValue();
		Servants servantsToConvert = new Servants(servantsNumber);
		CraftArea craftArea = board.getSpecificCraftArea(craftType);
		ActionSpace selectedActionSpace = craftArea.selectActionSpace(spaceType);
		CraftPlacement craftPlacement = new CraftPlacement(playerInAction, actionValue, selectedFamilyMember, servantsToConvert, craftArea, selectedActionSpace, board);
		return craftPlacement;
	}
	
	@Override
	public boolean checkAction() {
		return super.checkAction();
	}
	
	@Override
	public boolean checkPlaceRequirement() {
		return super.checkPlaceRequirement();
	}

	
	@Override
	public boolean checkOtherFamilyMember() {
		if (selectedFamilyMember.getFamilyMemberColor().equals(FamilyMemberColor.Neutral)) {
			return false;
		}
		else {
			return craftArea.checkCraftFamilyMemberPlayer(playerInAction);
		}
		
	}
	
	@Override
	public void Execute() {	
		super.Execute();
		CraftAction craftAction = new CraftAction(playerInAction, craftArea.getCraftType(), actionValue);
		int indexOfToCallBeforeCraftArray = this.playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects().size();
		ArrayList<ToCallBeforeCraft> effectsOnTheGo = this.playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects();
		for (int i = 0; i < indexOfToCallBeforeCraftArray; i++) {
			((Effect) effectsOnTheGo.get(indexOfToCallBeforeCraftArray)).activateEffect(playerInAction, craftAction);
		}
		craftAction.Execute();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String checkToString() {
		return super.checkToString();
	}
	
	/*public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);
		Game game = new Game();	
		Board board = game.getBoard();
		Player playerInAction = new Player("Santa", Color.Blue, game);
		Player playerInAction2 = new Player("Alex", Color.Green, game);
		FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Orange;
		FamilyMemberColor selectedFamilyMemberColor2 = FamilyMemberColor.Black;
		System.out.println("Insert Servants");
		int servantsNumber = tastiera.nextInt();
		System.out.println("Insert Space");
		int spaceType = tastiera.nextInt();
		System.out.println("Insert Servants");
		int servantsNumber2 = tastiera.nextInt();
		System.out.println("Insert Space");
		int spaceType2 = tastiera.nextInt();
		DevCardType towerType = DevCardType.Building;
		DevDeck towerTypeDeck  = new DevDeck(game, towerType, 1);
		board.getSpecificTower(towerType).pickCards(towerTypeDeck);
		TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(playerInAction, selectedFamilyMemberColor2, DevCardType.Building, 1, 1, board);
		if (towerPlacement.checkAction()) {
			towerPlacement.Execute();
		}
		System.out.println();
		CraftPlacement craftPlacement = factoryCraftPlacement(playerInAction, selectedFamilyMemberColor, board, servantsNumber, CraftType.Harvest, spaceType);
		CraftPlacement craftPlacement2 = factoryCraftPlacement(playerInAction2, selectedFamilyMemberColor2, board, servantsNumber2, CraftType.Production, spaceType2);
		boolean checkAction = craftPlacement.checkAction();
		System.out.println("Before:\n" + craftPlacement.toString());
		System.out.println("Check Action [ " + checkAction + " ]");
		System.out.println(craftPlacement.checkToString());
		if (checkAction) {
			craftPlacement.Execute();
		}
		System.out.println("After:\n" + craftPlacement.toString());
		boolean checkAction2 = craftPlacement2.checkAction();
		System.out.println("Before:\n" + craftPlacement2.toString());
		System.out.println("Check Action [ " + checkAction2 + " ]");
		System.out.println(craftPlacement2.checkToString());
		if (checkAction2) {
			craftPlacement2.Execute();
		}
		System.out.println("After:\n" + craftPlacement2.toString());
		System.out.println(board.toString());
	}
*/
}