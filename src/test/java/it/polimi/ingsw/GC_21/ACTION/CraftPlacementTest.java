package it.polimi.ingsw.GC_21.ACTION;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftPlacementTest {

	@Test
	public void test() {
		Game game = new Game("");	
		Board board = game.getBoard();
		Player playerInAction = new Player("Santa", Color.Blue, game);
		FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Orange;
		int servantsNumber = 0;
		int spaceType = 1;
		CraftPlacement craftPlacement = CraftPlacement.factoryCraftPlacement(playerInAction, selectedFamilyMemberColor, board, servantsNumber, CraftType.Harvest, spaceType);
		boolean checkAction = craftPlacement.checkAction();
		assertTrue(checkAction);
		assertTrue(!craftPlacement.checkBusyActionSpace());
		assertTrue(!craftPlacement.checkBlack());
		assertTrue(!craftPlacement.checkBusyFamilyMember());
		assertTrue(craftPlacement.checkDiceRequirement());
		assertTrue(!craftPlacement.checkOtherFamilyMember());

	}
	
	@Test
	public void executeTest() {
		

	}

}
