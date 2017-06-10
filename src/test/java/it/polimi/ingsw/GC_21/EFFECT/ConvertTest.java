package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.awt.Window.Type;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.Convert;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ConvertTest {

	/*@Test 
	public void testActivateEffectRewardsNoPrivilegesChoice1() {
		System.out.println("Type 0!!!");
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toPay2 = new Possession(0, 1, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 0, 1, 0, 0, 0, 0);
		Possession toTake2 = new Possession(0, 0, 0, 1, 0, 0, 0);
		Convert testConvert = new Convert(rewards, toPay1, toTake1, toPay2, toTake2, 0);
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testConvert.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		testPlayer2.getMyPersonalBoard().getMyPossession().subtract(toPay1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(toTake1);
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateEffectRewardsNoPrivilegesChoice2() {
		System.out.println("Type 1!!!");
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toPay2 = new Possession(0, 1, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 0, 1, 0, 0, 0, 0);
		Possession toTake2 = new Possession(0, 0, 0, 1, 0, 0, 0);
		Convert testConvert = new Convert(rewards, toPay1, toTake1, toPay2, toTake2, 0);
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testConvert.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		testPlayer2.getMyPersonalBoard().getMyPossession().subtract(toPay2);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(toTake2);
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateEffectRewards1PrivilegesChoice1With1Privilege() {
		System.out.println("Type 0!!! CONVERT PRIVILEGE 1!!!");
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toPay2 = new Possession(0, 1, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 0, 1, 0, 0, 0, 0);
		Possession toTake2 = new Possession(0, 0, 0, 1, 0, 0, 0);
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 1, 1, 0, 0, 0, 0));
		testPlayer2.getMyPersonalBoard().getMyPossession().subtract(toPay1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(toTake1);
		Convert testConvert = new Convert(rewards, toPay1, toTake1, toPay2, toTake2, 1);
		testConvert.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}

	public void testActivateEffectRewards2PrivilegesChoice1With2Privilege() {
		System.out.println("Type 0!!! CONVERT PRIVILEGE 1 & PRIVILEGE 2!!!");
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toPay2 = new Possession(0, 1, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 0, 1, 0, 0, 0, 0);
		Possession toTake2 = new Possession(0, 0, 0, 1, 0, 0, 0);
		Game testGame = new Game();
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 1, 1, 0, 0, 0, 0));
		testPlayer2.getMyPersonalBoard().getMyPossession().subtract(toPay1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 0, 0, 2, 0, 0, 0));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(toTake1);
		Convert testConvert = new Convert(rewards, toPay1, toTake1, toPay2, toTake2, 2);
		testConvert.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().toString());
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}*/
	
}
