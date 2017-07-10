package it.polimi.ingsw.GC_21.ACTION;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPlacementTest {

	@Test
	public void test() {
			Game game = new Game("santa");	
			Board board = game.getBoard();
			Player playerInAction = new Player("Santa", Color.Blue, game);
			FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Orange;
			int servantsNumber = 0;
			CouncilPlacement councilPlacement = CouncilPlacement.factoryCouncilPlacement(playerInAction, selectedFamilyMemberColor, board, servantsNumber);
			boolean checkAction = councilPlacement.checkAction();
			assertTrue(checkAction);
			assertTrue(!councilPlacement.checkBusyActionSpace());
			assertTrue(!councilPlacement.checkBlack());
			assertTrue(!councilPlacement.checkBusyFamilyMember());
			assertTrue(councilPlacement.checkDiceRequirement());
			assertTrue(!councilPlacement.checkOtherFamilyMember());
		
	}

}
