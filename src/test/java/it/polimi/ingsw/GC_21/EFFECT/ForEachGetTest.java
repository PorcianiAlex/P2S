package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.awt.event.MouseWheelEvent;
import java.io.IOException;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Coins;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Woods;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;
import it.polimi.ingsw.GC_21.VIEW.RmiAdapter;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class ForEachGetTest {
	
   @Test
	public void testActivateEffectForEachBuildingFiveWood() throws IOException {
	RmiClient rmiClient = new RmiClient(ViewType.CLI);
	RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
	ControllerManager controllerManager = new ControllerManager();
	RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
	Game testGame = new Game("Test");
	testGame.attachCurrent(remoteView);
	Player testPlayer = new Player("Test", Color.Blue, testGame);
	Player testPlayer2 = new Player("Test2", Color.Red, testGame);
	DevelopmentCard building = new DevelopmentCard("testCard");	
	building.setDevCardType(DevCardType.Building);
	testPlayer.getMyPersonalBoard().getSpecificOwnedCards(DevCardType.Building).add(building);
	ForEachGet forEachGetTest = new ForEachGet(testGame, null, 0, DevCardType.Building, 1, null, 0, ResourceType.Woods, 5, true);
	testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 5, 0, 0, 0, 0, 0));
	forEachGetTest.activateEffect(testPlayer, null);
	assertTrue(testPlayer.getMyPersonalBoard().getMyPossession().equals(testPlayer2.getMyPersonalBoard().getMyPossession()));
	}

	@Test
	public void testActivateEffectForEachCoinOneWood() throws IOException {
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		Game testGame = new Game("Test");
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(10, 10, 10, 10, 10, 10, 10));
		ForEachGet forEachGetTest = new ForEachGet(testGame, null, 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false);
		forEachGetTest.activateEffect(testPlayer, null);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(0, 10, 0, 0, 0, 0, 0));
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test
	public void testActivateForTwoWoodsOneCoin() throws IOException {
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		Game testGame = new Game("Test");
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().addItemToPossession(new Woods(2));
		ForEachGet forEachGetTest = new ForEachGet(testGame, null, 0, null, 0, ResourceType.Woods, 2, ResourceType.Coins, 1, false);
		forEachGetTest.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(new Possession(1, 2, 0, 0, 0, 0, 0));
		System.out.println(testPlayer2.getMyPersonalBoard().getMyPossession());
		System.out.println(testPlayer.getMyPersonalBoard().getMyPossession());
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	
}
