package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.PermanentLeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.VictoryPoints;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;
import it.polimi.ingsw.GC_21.VIEW.RmiAdapter;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class VictoryPointsInfluencerTest {

	@Test
	public void testActivateEffectForEachWood() throws IOException {
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		VictoryPointsInfluencer victoryPointsInfluencer = new VictoryPointsInfluencer(testGame, ResourceType.Woods, 1, 1, false);
		Player testPlayer = new Player("test", Color.Blue, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().addItemToPossession(new VictoryPoints(50));
		victoryPointsInfluencer.activateEffect(testPlayer, null);
		int actual = testPlayer.getMyPersonalBoard().getMyPossession().getVictoryPoints().getValue();
		int expected = 50;
		assertTrue(actual == expected);
	}

	
	@Test
	public void testActivateEffectForEachResource() throws IOException {
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		VictoryPointsInfluencer victoryPointsInfluencer = new VictoryPointsInfluencer(testGame, null, 1, 1, true);
		Player testPlayer = new Player("test", Color.Blue, testGame);
		testPlayer.getMyPersonalBoard().getMyPossession().add(new Possession(5, 5, 5, 5, 5, 5, 5));
		testPlayer.getMyPersonalBoard().getMyPossession().addItemToPossession(new VictoryPoints(50));
		victoryPointsInfluencer.activateEffect(testPlayer, null);
		int actual = testPlayer.getMyPersonalBoard().getMyPossession().getVictoryPoints().getValue();
		int expected = 35;
		assertTrue(actual == expected);
	}

}
