package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ForEachGetTest {
	
	@Test
	public void testActivateEffectEachBuildingFiveWood1Privilege() {
	System.out.println("---NEW TEST---");
	System.out.println("---CHOOSE COINS---");
	Game testGame = new Game();
	Player testPlayer = new Player("Test", Color.Blue, testGame);
	Player testPlayer2 = new Player("Test2", Color.Red, testGame);
	DevelopmentCard building = new DevelopmentCard("testCard");	
	building.setDevCardType(DevCardType.Building);
	testPlayer.getMyPersonalBoard().getOwnedCards(DevCardType.Building).add(building);
	ForEachGet forEachGetTest = new ForEachGet(null, 1, DevCardType.Building, 1, null, 0, ResourceType.Woods, 5, true);
	testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(2, 5, 0, 0, 0, 0, 0));
	forEachGetTest.activateEffect(testPlayer, null);
	System.out.println(testPlayer.getMyPersonalBoard().getOwnedCards(DevCardType.Building).getOwnedCardsnumber());
	System.out.println(testPlayer.getMyPersonalBoard().getOwnedCards(DevCardType.Building).getMyOwnedCards()[0].toString());
	System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
	System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
	assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(testPlayer2.getMyPersonalBoard().getMyPossession()));
	}

	public void testActivateEffectEachBuildingFiveWood() {
	System.out.println("---NEW TEST---");
	Game testGame = new Game();
	Player testPlayer = new Player("Test", Color.Blue, testGame);
	Player testPlayer2 = new Player("Test2", Color.Red, testGame);
	DevelopmentCard building = new DevelopmentCard("testCard");	
	building.setDevCardType(DevCardType.Building);
	testPlayer.getMyPersonalBoard().getOwnedCards(DevCardType.Building).add(building);
	ForEachGet forEachGetTest = new ForEachGet(null, 0, DevCardType.Building, 1, null, 0, ResourceType.Woods, 5, true);
	testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 5, 0, 0, 0, 0, 0));
	forEachGetTest.activateEffect(testPlayer, null);
	System.out.println(testPlayer.getMyPersonalBoard().getOwnedCards(DevCardType.Building).getOwnedCardsnumber());
	System.out.println(testPlayer.getMyPersonalBoard().getOwnedCards(DevCardType.Building).getMyOwnedCards()[0].toString());
	System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
	System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
	assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(testPlayer2.getMyPersonalBoard().getMyPossession()));
	}

	
	public void testActivateEffectEachCoinOneWood() {
		System.out.println("---NEW TEST---");
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false);
		forEachGetTest.activateEffect(testPlayer, null);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 15, 0, 0, 0, 0, 0));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	

	public void testActivateTwoWoodsOneCoin() {
		System.out.println("---NEW TEST---");
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 0, null, 0, ResourceType.Woods, 2, ResourceType.Coins, 1, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(5, 0, 0, 0, 0, 0, 0));
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateOneWoodTwoCoins() {
		System.out.println("---NEW TEST---");
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 0, null, 0, ResourceType.Woods, 1, ResourceType.Coins, 2, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(30, 0, 0, 0, 0, 0, 0));
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}

	@Test
	public void testActivateOneWoodTwoCoins1Privilege() {
		System.out.println("---NEW TEST---");
		System.out.println("CHOOSE MILITARY POINTS!");
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 1, null, 0, ResourceType.Woods, 1, ResourceType.Coins, 2, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(20, 0, 0, 0, 0, 2, 0));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(20, 10, 10, 10, 10, 10, 10));		
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());

		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateOneWoodTwoCoins2Privilege() {
		System.out.println("---NEW TEST---");
		System.out.println("CHOOSE MILITARY POINTS & COINS!");
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 2, null, 0, ResourceType.Woods, 1, ResourceType.Coins, 2, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(22, 0, 0, 0, 0, 2, 0));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(20, 10, 10, 10, 10, 10, 10));
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	
}
