package it.polimi.ingsw.GC_21.BOARD;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftAreaTest {

	@Test
	public void testCheckCraftFamilyMemberPlayer() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Blue, testGame);
		testGame.getBoard().getHarvestArea().getSingleActionSpace().placeFamilyMember(testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange));
		boolean result = 	testGame.getBoard().getHarvestArea().checkCraftFamilyMemberPlayer(testPlayer);
		assertTrue(result);
	}

	@Test
	public void testCheckPlayerPresenceOnMultipleSpace() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Blue, testGame);
		testGame.getBoard().getHarvestArea().getMultipleActionSpace().placeFamilyMember(testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange));
		boolean result = 	testGame.getBoard().getHarvestArea().checkCraftFamilyMemberPlayer(testPlayer);
		assertTrue(result);
	}

}
