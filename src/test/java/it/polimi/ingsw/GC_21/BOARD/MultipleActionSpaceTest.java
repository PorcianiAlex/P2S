package it.polimi.ingsw.GC_21.BOARD;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class MultipleActionSpaceTest {

	@Test
	public void testPlaceFamilyMember() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Blue, testGame);
		MultipleActionSpace test = new MultipleActionSpace(1, null, testGame);
		test.placeFamilyMember(testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange));
		assertTrue(test.getFamilyMembers().get(0).equals(testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange)));
	}

}
