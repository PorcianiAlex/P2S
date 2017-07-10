package it.polimi.ingsw.GC_21.ACTION;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.BOARD.Dice;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.EFFECT.PlacementInfluencer;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementActionTest {

	@Test
	public void callBeforePlacementEmptyArray() {
		Game game = new Game("");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).setActionValue(3);
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 3, 1, game.getBoard());
		testAction.callBeforePlacementEffects();
		int ActualActionValue = testAction.getActionValue();
		Possession ActualDiscount = testAction.getDiscount();
		int ExpectedActionValue = 3;
		Possession ExpectedDiscount = new Possession(0,0,0,0,0,0,0);
		assertTrue(ActualActionValue==ExpectedActionValue);
		assertTrue(ActualDiscount.equals(ExpectedDiscount));
	}
	
	@Test
	public void callBeforePlacementEffectsBuildingsAndTerritories() {
		Game game = new Game("");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		Possession testDiscount = new Possession(1, 1, 1, 0, 0, 0, 0);
		PlacementInfluencer test1 = new PlacementInfluencer(1, DevCardType.Building, testDiscount);
		PlacementInfluencer test2 = new PlacementInfluencer(1, DevCardType.Territory, testDiscount);
		PlacementInfluencer test3 = new PlacementInfluencer(1, DevCardType.Building, testDiscount);
		testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().add(test1);
		testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().add(test2);
		testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().add(test3);
		testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getAssociatedDice().setValue(3);
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 3, 1, game.getBoard());
		testAction.callBeforePlacementEffects();
		int ActualActionValue = testAction.getActionValue();
		Possession ActualDiscount = testAction.getDiscount();
		int ExpectedActionValue = 5;
		Possession ExpectedDiscount = new Possession(2,2,2,0,0,0,0);
		assertTrue(ActualDiscount.equals(ExpectedDiscount));
	}

	
	@Test
	public void callBeforePlacementEffectsBuildings() {
		Game game = new Game("");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		Possession testDiscount = new Possession(1, 1, 1, 0, 0, 0, 0);
		PlacementInfluencer test1 = new PlacementInfluencer(1, DevCardType.Building, testDiscount);
		PlacementInfluencer test2 = new PlacementInfluencer(1, DevCardType.Building, testDiscount);
		PlacementInfluencer test3 = new PlacementInfluencer(1, DevCardType.Building, testDiscount);
		testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().add(test1);
		testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().add(test2);
		testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().add(test3);
		testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).setActionValue(3);
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 3, 1, game.getBoard());
		testAction.callBeforePlacementEffects();
		int ActualActionValue = testAction.getActionValue();
		Possession ActualDiscount = testAction.getDiscount();
		int ExpectedActionValue = 6;
		Possession ExpectedDiscount = new Possession(3,3,3,0,0,0,0);
		assertTrue(ActualActionValue==ExpectedActionValue);
	}

	
	@Test
	public void testTowerPlacement(){
		Game game = new Game("");	
		Player playerInAction = new Player("Santa", Color.Blue, game);
		Dice dice = new Dice();
		FamilyMember selectedFamilyMember = new FamilyMember(dice, playerInAction);
		SingleActionSpace singleActionSpace = new SingleActionSpace(selectedFamilyMember.getActionValue(), null, game);
		PlacementAction placementAction = new PlacementAction(playerInAction, selectedFamilyMember.getActionValue(), selectedFamilyMember, singleActionSpace, null, null, new Servants(0), game.getBoard());
		boolean checkAction = placementAction.checkAction();
		assertTrue(checkAction);
		assertTrue(!placementAction.checkBusyActionSpace());
		assertTrue(!placementAction.checkBlack());
		assertTrue(!placementAction.checkBusyFamilyMember());
		assertTrue(placementAction.checkDiceRequirement());
		assertTrue(!placementAction.checkOtherFamilyMember());
	}
}
