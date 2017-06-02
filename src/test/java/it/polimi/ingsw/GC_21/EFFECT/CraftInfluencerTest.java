package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftInfluencerTest {

	@Test
	public void testActivateEffectProductionFalse() {
		CraftInfluencer testCraftInfluencer = new CraftInfluencer(CraftType.Production, 2);
		Player testPlayer = new Player("ale", Color.Blue, new Game());
		CraftAction testCraftAction = new CraftAction(testPlayer, CraftType.Production, 3);
		testCraftInfluencer.activateEffect(testPlayer, testCraftAction);
		int actual = testCraftAction.getActionValue();
		int expected = 6;
		assertFalse(actual==expected);
	}
	
	@Test
	public void testActivateEffectProduction() {
		CraftInfluencer testCraftInfluencer = new CraftInfluencer(CraftType.Production, 2);
		Player testPlayer = new Player("ale", Color.Blue, new Game());
		CraftAction testCraftAction = new CraftAction(testPlayer, CraftType.Production, 3);
		testCraftInfluencer.activateEffect(testPlayer, testCraftAction);
		int actual = testCraftAction.getActionValue();
		int expected = 5;
		assertTrue(actual==expected);
	}
	
	@Test
	public void testActivateEffectHarvest() {
		CraftInfluencer testCraftInfluencer = new CraftInfluencer(CraftType.Harvest, 2);
		Player testPlayer = new Player("ale", Color.Blue, new Game());
		CraftAction testCraftAction = new CraftAction(testPlayer, CraftType.Harvest, 3);
		testCraftInfluencer.activateEffect(testPlayer, testCraftAction);
		int actual = testCraftAction.getActionValue();
		int expected = 5;
		assertTrue(actual==expected);
	}

}
