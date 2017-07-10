package it.polimi.ingsw.GC_21.ACTION;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ExcommActionTest {

	@Test
	public void test() {
		Game game = new Game("");
		Player playerInAction = new Player("santa", Color.Black, game);
		try {
			ExcommAction excommAction = new ExcommAction(playerInAction, game, true);
			excommAction.Execute();
			boolean checkAction = excommAction.checkAction();
			assertTrue(checkAction);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Game game = new Game("");
		Player playerInAction = new Player("santa", Color.Black, game);
		playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().setValue(15);
		try {
			ExcommAction excommAction = new ExcommAction(playerInAction, game, false);
			excommAction.Execute();
			boolean checkAction = excommAction.checkAction();
			assertTrue(playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue() == 0);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
