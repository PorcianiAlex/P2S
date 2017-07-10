package it.polimi.ingsw.GC_21.ACTION;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class MarketPlacementTest {

	@Test
	public void test() {
			Game game = new Game("santa");	
			Board board = game.getBoard();
			Player playerInAction = new Player("Santa", Color.Blue, game);
			FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Neutral;
			int position= 2;
			int servantsNumber = 0;
			MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(playerInAction, selectedFamilyMemberColor, position, servantsNumber, board);
			boolean checkAction = marketPlacement.checkAction();
			assertTrue(!checkAction);
			assertTrue(!marketPlacement.checkBusyActionSpace());
			assertTrue(!marketPlacement.checkBlack());
			assertTrue(!marketPlacement.checkBusyFamilyMember());
			assertTrue(!marketPlacement.checkDiceRequirement());
			assertTrue(!marketPlacement.checkOtherFamilyMember());

			
	}

}
