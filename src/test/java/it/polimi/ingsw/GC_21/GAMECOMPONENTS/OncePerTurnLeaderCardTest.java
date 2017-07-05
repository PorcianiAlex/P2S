package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class OncePerTurnLeaderCardTest {

	@Test
	public void test() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Black, testGame);
		OncePerTurnLeaderCard bartolomeoColleoni = new OncePerTurnLeaderCard("20", "Bartolomeo Colleoni", 2, 0, 0, 4, new Possession(), false, new Effect(new Possession(0, 0, 0, 0, 0, 0, 4), 0, testGame));
		bartolomeoColleoni.callEffect(testPlayer);
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(new Possession(0, 0, 0, 0, 0, 0, 4)));
	}

}
