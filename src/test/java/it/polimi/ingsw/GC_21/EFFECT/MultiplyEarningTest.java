package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class MultiplyEarningTest {

	@Test
	public void testSetNewReward() {
		Game testGame = new Game("aaa");
		MultiplyEarning multiplyEarning = new MultiplyEarning(2);
		Player testPlayer = new Player("aaa", Color.Blue, testGame);
		Possession rewards1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		testGame.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getSingleActionSpace().setActionSpaceEffect(new Effect(rewards1, 0, testGame));
		TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 0, testGame.getBoard());
		multiplyEarning.activateEffect(testPlayer, towerPlacement);
		Possession actual = testGame.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getSingleActionSpace().getActionSpaceEffect().getRewards();
		Possession expected = new Possession(2, 0, 0, 0, 0, 0, 0);
		assertTrue(actual.equals(expected));
	}

}
