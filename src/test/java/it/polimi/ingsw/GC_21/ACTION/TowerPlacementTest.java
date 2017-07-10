package it.polimi.ingsw.GC_21.ACTION;

import static org.junit.Assert.*;
import org.junit.Test;

import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.EFFECT.CraftInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.PlacementInfluencer;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class TowerPlacementTest {

	@Test
	public void testEarnPermanentEffectNull() {
		Game game = new Game("test");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		DevelopmentCard test1 = new DevelopmentCard("aaa");
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testAction.earnPermanentEffect(test1);
		int MyPermSize1 = testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().size();
		int MyPermSize2 = testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().size();
		assertTrue(0==MyPermSize1);
		assertTrue(0==MyPermSize2);
	}
	
	@Test
	public void testEarnPermanentEffect() {
		Game game = new Game("test");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		DevelopmentCard test2 = new DevelopmentCard("aaa");
		PlacementInfluencer testEffect2 = new PlacementInfluencer(1, DevCardType.Building, new Possession());
		test2.setSecondaryEffect(testEffect2);
		DevelopmentCard test1 = new DevelopmentCard("aaa");
		CraftInfluencer testEffect = new CraftInfluencer(CraftType.Harvest, 1, game);
		test1.setSecondaryEffect(testEffect);
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testAction.earnPermanentEffect(test1);
		testAction.earnPermanentEffect(test2);
		Effect expected1 = test1.getSecondaryEffect();
		Effect actual1 = ((Effect) testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().get(0));
		assertTrue(actual1.equals(expected1));
		Effect expected2 = test2.getSecondaryEffect();
		Effect actual2 = ((Effect) testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().get(0));
		assertTrue(actual2.equals(expected2));
	}
	
	@Test
	public void testEarnPermanentEffect2() {
		Game game = new Game("test");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		DevelopmentCard test1 = new DevelopmentCard("aaa");
		CraftInfluencer testEffect = new CraftInfluencer(CraftType.Harvest, 1, game);
		test1.setSecondaryEffect(testEffect);
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testAction.earnPermanentEffect(test1);
		Effect expected = test1.getSecondaryEffect();
		Effect actual = ((Effect) testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().get(0));
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void testEarnPermanentEffect3() {
		Game game = new Game("test");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		DevelopmentCard test1 = new DevelopmentCard("aaa");
		PlacementInfluencer testEffect = new PlacementInfluencer(1, DevCardType.Building, new Possession());
		test1.setSecondaryEffect(testEffect);
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testAction.earnPermanentEffect(test1);
		Effect expected = test1.getSecondaryEffect();
		Effect actual = ((Effect) testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().get(0));
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void testTowerPlacement(){
		Game game = new Game("");	
		Player playerInAction = new Player("Santa", Color.Blue, game);
		FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Neutral;
		TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(playerInAction, selectedFamilyMemberColor, DevCardType.Building, 1, 0, game.getBoard());
		boolean checkAction = towerPlacement.checkAction();
		assertTrue(!checkAction);
		assertTrue(!towerPlacement.checkBusyActionSpace());
		assertTrue(!towerPlacement.checkBlack());
		assertTrue(!towerPlacement.checkBusyFamilyMember());
		assertTrue(!towerPlacement.checkDiceRequirement());
		assertTrue(!towerPlacement.checkOtherFamilyMember());
	}
	
	@Test
	public void testCheckAction(){
		Game game = new Game("");	
		Player playerInAction = new Player("Santa", Color.Blue, game);
		Player playerInAction2 = new Player("Santa2", Color.Red, game);
		FamilyMemberColor selectedFamilyMemberColor2 = FamilyMemberColor.Orange;
		TowerPlacement towerPlacement2 = TowerPlacement.factoryTowerPlacement(playerInAction2, selectedFamilyMemberColor2, DevCardType.Building, 3, 0, game.getBoard());
		towerPlacement2.Execute();
		FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Neutral;
		TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(playerInAction, selectedFamilyMemberColor, DevCardType.Building, 1, 0, game.getBoard());
		boolean checkAction = towerPlacement.checkAction();
		assertTrue(!checkAction);
	}
	@Test
	public void testCheckCardRequirement(){
		Game game = new Game("");	
		Player playerInAction = new Player("Santa", Color.Blue, game);
		playerInAction.getMyPersonalBoard().setMyPossession(new Possession(5,5,5,5,5,5,5));
		DevelopmentCard dv = new DevelopmentCard("test");
		dv.setRequirements(new Possession(1,0,0,0,0,0,0));
		game.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getDevCardPlace().setCard(dv);
		TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(playerInAction, FamilyMemberColor.Orange, DevCardType.Building, 1, 0, game.getBoard());
		assertTrue(towerPlacement.checkCardRequirements(playerInAction.getMyPersonalBoard()));
		assertTrue(towerPlacement.checkTakeabilityCard(playerInAction.getMyPersonalBoard(), DevCardType.Territory));
	}
	
	@Test
	public void testPayCardRequirement(){
		Game game = new Game("");	
		Player playerInAction = new Player("Santa", Color.Blue, game);
		playerInAction.getMyPersonalBoard().setMyPossession(new Possession(5,5,5,5,5,5,5));
		DevelopmentCard dv = new DevelopmentCard("test");
		dv.setRequirements(new Possession(1,0,0,0,0,0,0));
		game.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getDevCardPlace().setCard(dv);
		TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(playerInAction, FamilyMemberColor.Orange, DevCardType.Building, 1, 0, game.getBoard());
		towerPlacement.pay();
		assertTrue(playerInAction.getMyPersonalBoard().getMyPossession().equals((new Possession(4,5,5,5,5,5,5))));
	}
	
	
	@Test
	public void testTakeCard(){
		Game game = new Game("");	
		Player playerInAction = new Player("Santa", Color.Blue, game);
		TowerPlacement test = TowerPlacement.factoryTakeCard(playerInAction, DevCardType.Building, 1, 1, new Possession(), new Possession(), game.getBoard());
		test.Execute();
		assertTrue(playerInAction.getMyPersonalBoard().getMyPossession().equals(new Possession()));
	}

}
