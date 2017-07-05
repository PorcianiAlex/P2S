package it.polimi.ingsw.GC_21.BOARD;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class SingleActionSpaceTest {

	@Test
	public void testPlaceFamilyMember() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Blue, testGame);
		SingleActionSpace test = new SingleActionSpace(1, null, testGame);
		test.placeFamilyMember(testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange));
		assertTrue(test.getFamilyMemberLocated().equals(testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange)));
	}

}
