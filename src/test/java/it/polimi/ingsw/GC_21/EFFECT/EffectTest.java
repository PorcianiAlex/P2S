package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class EffectTest {

	@Test
	public void testActivateEffect1privileges() {
		System.out.println("CHOOSE PRIVILEGE CONVERSION 1");
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 1, 1, 0, 0, 0, 0));
		Effect testEffect = new Effect(rewards, 1);
		testEffect.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(testPlayer2.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateEffect2privileges() {
		System.out.println("CHOOSE PRIVILEGE CONVERSION 1&2");
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 1, 1, 0, 0, 0, 0));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 0, 0, 2, 0, 0, 0));
		Effect testEffect = new Effect(rewards, 2);
		testEffect.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(testPlayer2.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateEffect5privileges() {
		System.out.println("CHOOSE PRIVILEGE CONVERSION 1,2,3,4,5");
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(2, 1, 1, 2, 1, 2, 0));
		Effect testEffect = new Effect(rewards, 5);
		testEffect.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(testPlayer2.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateEffect0privileges() {
		System.out.println("NO PRIVILEGES!");
		Player testPlayer = new Player("Test", Color.Blue);
		Player testPlayer2 = new Player("Test2", Color.Red);
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		Effect testEffect = new Effect(rewards, 0);
		testEffect.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(testPlayer2.getMyPersonalBoard().getMyPossession()));
	}
}
