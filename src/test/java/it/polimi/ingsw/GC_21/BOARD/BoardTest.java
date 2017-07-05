package it.polimi.ingsw.GC_21.BOARD;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class BoardTest {

	@Test
	public void testRefreshBoard() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Blue, testGame);
		PlacementAction testAction = new PlacementAction(testPlayer, 3, testPlayer.getSpecificFamilyMember(FamilyMemberColor.Orange), testGame.getBoard().getMarketArea().getSingleActionSpace()[0], new Servants(0), testGame.getBoard());
		testAction.Execute();
		testGame.getBoard().refreshBoard();
		assertTrue(!testGame.getBoard().getMarketArea().getSingleActionSpace()[0].isBusy());
		
	}

}
