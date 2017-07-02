package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.io.IOException;
import java.rmi.RemoteException;

import org.junit.Test;
import org.omg.IOP.TAG_MULTIPLE_COMPONENTS;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;
import it.polimi.ingsw.GC_21.VIEW.RmiAdapter;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class CraftInfluencerTest {

	@Test
	public void testActivateEffectProductionFalse() throws IOException {
		Game testGame = new Game("test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		CraftInfluencer testCraftInfluencer = new CraftInfluencer(CraftType.Production, 2, testGame);
		Player testPlayer = new Player("ale", Color.Blue, testGame);
		CraftAction testCraftAction = new CraftAction(testPlayer, CraftType.Production, 3);
		testCraftInfluencer.activateEffect(testPlayer, testCraftAction);
		int actual = testCraftAction.getActionValue();
		int expected = 6;
		assertFalse(actual==expected);
	}
	
	@Test
	public void testActivateEffectProduction() throws IOException {
		Game testGame = new Game("test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		CraftInfluencer testCraftInfluencer = new CraftInfluencer(CraftType.Production, 2, testGame);
		Player testPlayer = new Player("ale", Color.Blue, testGame);
		CraftAction testCraftAction = new CraftAction(testPlayer, CraftType.Production, 3);
		testCraftInfluencer.activateEffect(testPlayer, testCraftAction);
		int actual = testCraftAction.getActionValue();
		int expected = 5;
		assertTrue(actual==expected);
	}
	
	@Test
	public void testActivateEffectHarvest() throws IOException {
		Game testGame = new Game("test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		CraftInfluencer testCraftInfluencer = new CraftInfluencer(CraftType.Harvest, 2, testGame);
		Player testPlayer = new Player("ale", Color.Blue, testGame);
		CraftAction testCraftAction = new CraftAction(testPlayer, CraftType.Harvest, 3);
		testCraftInfluencer.activateEffect(testPlayer, testCraftAction);
		int actual = testCraftAction.getActionValue();
		int expected = 5;
		assertTrue(actual==expected);
	}
	

}
