package it.polimi.ingsw.GC_21.BOARD;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class TowerTest {

	@Test
	public void checkFMPresenceTest() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Blue, testGame);
		Tower.factoryTowers(testGame);
		testGame.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getSingleActionSpace().placeFamilyMember(testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange));
		boolean result = testGame.getBoard().getSpecificTower(DevCardType.Building).checkFamilyMemberPresence();
		assertTrue(result);
	}
	
	@Test public void checkFMOfMineTest(){
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Blue, testGame);
		Tower.factoryTowers(testGame);
		testGame.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getSingleActionSpace().placeFamilyMember(testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange));
		boolean result = testGame.getBoard().getSpecificTower(DevCardType.Building).checkTowerFamilyMemberOfMine(testPlayer);
		assertTrue(result);
	}

}
