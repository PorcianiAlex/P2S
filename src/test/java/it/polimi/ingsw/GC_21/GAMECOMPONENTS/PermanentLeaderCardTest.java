package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.EFFECT.DontCheckMP;
import it.polimi.ingsw.GC_21.EFFECT.PlacementInfluencer;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PermanentLeaderCardTest {

	@Test
	public void callEffectTest() {
		Game testGame = new Game("aaa");
		Player testPlayer = new Player("aaa", Color.Black, testGame);
		PermanentLeaderCard picoDellaMirandola = new PermanentLeaderCard("11", "Pico della Mirandola", 4, 0, 2, 0, new Possession(), false, new PlacementInfluencer(0, null, new Possession(3, 0, 0, 0, 0, 0, 0)));
		picoDellaMirandola.callEffect(testPlayer);
		assertTrue(testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().size()==1);
	}

}
