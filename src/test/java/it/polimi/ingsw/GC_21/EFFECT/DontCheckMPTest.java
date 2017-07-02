package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.io.IOException;
import java.rmi.RemoteException;

import org.junit.Test;

import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;
import it.polimi.ingsw.GC_21.VIEW.RmiAdapter;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class DontCheckMPTest {

	@Test
	public void activateEffectTest() throws IOException {
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		DontCheckMP dontCheckMP = new DontCheckMP();
		dontCheckMP.activateEffect(testPlayer, null);
		assertTrue(testPlayer.isCheckOnMP());
		
	}

}
