package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ForEachGetTest {

	@Test
	public void testActivateEffectEachCoinOneWood() {
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 10, 0, 0, 0, 0, 0));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateTwoWoodsOneCoin() {
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 0, null, 0, ResourceType.Woods, 2, ResourceType.Coins, 1, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(5, 0, 0, 0, 0, 0, 0));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateOneWoodTwoCoins() {
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 0, null, 0, ResourceType.Woods, 1, ResourceType.Coins, 2, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(20, 0, 0, 0, 0, 0, 0));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}

	@Test
	public void testActivateOneWoodTwoCoins1Privilege() {
		System.out.println("CHOOSE MILITARY POINTS!");
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 1, null, 0, ResourceType.Woods, 1, ResourceType.Coins, 2, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(20, 0, 0, 0, 0, 2, 0));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateOneWoodTwoCoins2Privilege() {
		System.out.println("CHOOSE MILITARY POINTS & COINS!");
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(null, 2, null, 0, ResourceType.Woods, 1, ResourceType.Coins, 2, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(22, 0, 0, 0, 0, 2, 0));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
}
