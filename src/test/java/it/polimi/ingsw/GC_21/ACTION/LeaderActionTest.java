package it.polimi.ingsw.GC_21.ACTION;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.EFFECT.DontCheckMP;
import it.polimi.ingsw.GC_21.EFFECT.EarningInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.PermanentLeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.REMOTEVIEW.RemoteView;
import it.polimi.ingsw.GC_21.REMOTEVIEW.RmiAdapter;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class LeaderActionTest  {

	@Test
	public void testExecute() throws IOException {
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		PermanentLeaderCard testCard3 = new PermanentLeaderCard("aa", "test2", 0, 0, 0, 0, new Possession(), false, new EarningInfluencer(new Possession()));
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		LeaderAction leaderAction = new LeaderAction(testPlayer, testCard3, true, testGame);
		leaderAction.Execute();
		assertTrue(testPlayer.getMyPersonalBoard().getToCallWhenEarningEffects().size()==1);
	}
	
	@Test
	public void testExecuteDontCheckOnMP() throws IOException {
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		PermanentLeaderCard testCard3 = new PermanentLeaderCard("aa", "test2", 0, 0, 0, 0, new Possession(), false, new DontCheckMP());
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		LeaderAction leaderAction = new LeaderAction(testPlayer, testCard3, true, testGame);
		leaderAction.Execute();
		assertTrue(testPlayer.isCheckOnMP());
	}
	
	
	@Test
	public void testExecuteDontSatisfyReq() throws IOException {
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		OncePerTurnLeaderCard testCard3 = new OncePerTurnLeaderCard("aa", "test2", 0, 0, 0, 0, new Possession(10,10,0,0,0,0,0), false, new Effect(new Possession(5, 0, 0, 0, 0, 0, 0), 0, testGame));
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		testPlayer.getMyPersonalBoard().setMyPossession(new Possession(5, 5, 5, 5, 5, 5, 5));
		LeaderAction leaderAction = new LeaderAction(testPlayer, testCard3, true, testGame);
		leaderAction.Execute();
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(5, 5, 5, 5, 5, 5, 5);
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void testExecutePlay() throws IOException {
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		OncePerTurnLeaderCard testCard3 = new OncePerTurnLeaderCard("aa", "test2", 0, 0, 0, 0, new Possession(), false, new Effect(new Possession(5, 0, 0, 0, 0, 0, 0), 0, testGame));
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		testGame.attachCurrent(remoteView);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		testPlayer.getMyPersonalBoard().setMyPossession(new Possession(10, 5, 5, 5, 5, 5, 5));
		testPlayer.getMyPersonalBoard().getPlayedOncePerTurnLeaderCards().add(testCard3);
		LeaderAction leaderAction = new LeaderAction(testPlayer, testCard3, false, testGame);
		leaderAction.Execute();
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(10, 5, 5, 5, 5, 5, 5);
		assertTrue(actual.equals(expected));
	}

}
