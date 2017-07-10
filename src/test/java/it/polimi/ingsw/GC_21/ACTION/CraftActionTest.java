package it.polimi.ingsw.GC_21.ACTION;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.CraftInfluencer;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;


public class CraftActionTest {

	@Test
	public void testCallBeforeCraftEffectsProduction2() {
		Game game = new Game("Test");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		CraftInfluencer test1 = new CraftInfluencer(CraftType.Production, 1, game);
		CraftInfluencer test2 = new CraftInfluencer(CraftType.Harvest, 1, game);
		CraftInfluencer test3 = new CraftInfluencer(CraftType.Production, 1, game);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test1);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test2);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test3);
		testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getAssociatedDice().setValue(3);
		CraftAction testAction = new CraftAction(testPlayer, CraftType.Harvest, 3);
		testAction.callBeforeCraftEffects();
		int expectedActionValue = 4;
		int actualActionValue = testAction.getActionValue();
		assertTrue(expectedActionValue==actualActionValue);
	}
	
	
	@Test
	public void testCallBeforeCraftEffectsProduction() {
		Game game = new Game("Test");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		CraftInfluencer test1 = new CraftInfluencer(CraftType.Production, 1, game);
		CraftInfluencer test2 = new CraftInfluencer(CraftType.Production, 1, game);
		CraftInfluencer test3 = new CraftInfluencer(CraftType.Production, 1, game);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test1);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test2);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test3);
		testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getAssociatedDice().setValue(3);
		CraftAction testAction = new CraftAction(testPlayer, CraftType.Production, 3);
		testAction.callBeforeCraftEffects();
		int expectedActionValue = 6;
		int actualActionValue = testAction.getActionValue();
		assertTrue(expectedActionValue==actualActionValue);
	}
	
	@Test
	public void testCallBeforeCraftEffectsHarvest() {
		Game game = new Game("Test");
		Player testPlayer = new Player("aaa", Color.Blue, game);
		CraftInfluencer test1 = new CraftInfluencer(CraftType.Harvest, 1, game);
		CraftInfluencer test2 = new CraftInfluencer(CraftType.Harvest, 1, game);
		CraftInfluencer test3 = new CraftInfluencer(CraftType.Harvest, 1, game);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test1);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test2);
		testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().add(test3);
		testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getAssociatedDice().setValue(3);
		CraftAction testAction = new CraftAction(testPlayer, CraftType.Harvest, 3);
		testAction.callBeforeCraftEffects();
		int expectedActionValue = 6;
		int actualActionValue = testAction.getActionValue();
		testAction.Execute();

		assertTrue(expectedActionValue==actualActionValue);
	}

}
