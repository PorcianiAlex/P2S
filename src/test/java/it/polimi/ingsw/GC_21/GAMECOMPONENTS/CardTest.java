package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.EffectType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CardTest {

	@Test
	public void testCallCraftEffect() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Black, testGame);
		DevelopmentCard testCard = new DevelopmentCard("test");
		testCard.setSecondaryEffect(new Effect(new Possession(5, 0, 0, 0, 0, 0, 0), 0, testGame));
		testCard.callCraftEffect(testPlayer);
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(new Possession(5, 0, 0, 0, 0, 0, 0)));

	}

	@Test
	public void testCallEffect() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Black, testGame);
		DevelopmentCard testCard = new DevelopmentCard("test");
		testCard.setImmediateEffect(new Effect(new Possession(5, 0, 0, 0, 0, 0, 0), 0, testGame));
		testCard.callEffect(EffectType.Immediate, testPlayer, null);
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(new Possession(5, 0, 0, 0, 0, 0, 0)));
	}

}
