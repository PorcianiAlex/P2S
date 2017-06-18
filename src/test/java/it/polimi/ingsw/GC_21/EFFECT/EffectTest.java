package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.Pass;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;
import it.polimi.ingsw.GC_21.VIEW.RmiAdapter;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class EffectTest {
	
	
	
	@Test
	public void testActivateEffect0privileges() throws IOException {
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		Game testGame = new Game("Test");
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		Effect testEffect = new Effect(rewards, 0, testGame);
		testEffect.activateEffect(testPlayer, null);
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(testPlayer2.getMyPersonalBoard().getMyPossession()));
	}
	

	@Test
	public void testActivateEffectNullPlayer() throws IOException {
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		Game testGame = new Game("Test");
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		Effect testEffect = new Effect(rewards, 0, testGame);
		testEffect.activateEffect(null, new Pass(testPlayer));
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(new Possession(5, 5, 5, 5, 5, 5, 5)));
	}
	
	@Test
	public void testActivateEffectNullPlayerNullAction() throws IOException {
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		Game testGame = new Game("Test");
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		Effect testEffect = new Effect(rewards, 0, testGame);
		testEffect.activateEffect(null, null);
		assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(new Possession(5, 5, 5, 5, 5, 5, 5)));
	}
	
}
