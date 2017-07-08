package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.awt.Window.Type;
import java.io.IOException;
import java.rmi.RemoteException;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.CLIENT.MainClient;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.EFFECT.Convert;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.REMOTEVIEW.AdapterConnection;
import it.polimi.ingsw.GC_21.REMOTEVIEW.InputForm;
import it.polimi.ingsw.GC_21.REMOTEVIEW.RemoteView;
import it.polimi.ingsw.GC_21.REMOTEVIEW.RmiAdapter;
import it.polimi.ingsw.GC_21.REMOTEVIEW.Server;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class ConvertTest {
	
	//Not testing here the interaction with user because it passes through connections
	
	@Test 
	public void testActivateEffectRewardsNoPrivileges() throws IOException {
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 0, 1, 0, 0, 0, 0);
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		Convert testConvert = new Convert(testGame, rewards, toPay1, toTake1, new Possession(), new Possession(), 0);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testGame.attachCurrent(remoteView);
		remoteView.setPlayer(testPlayer);
		testConvert.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		testPlayer2.getMyPersonalBoard().getMyPossession().subtract(toPay1);
		testPlayer2.getMyPersonalBoard().getMyPossession().add(toTake1);
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	@Test 
	public void testActivateEffectRewardsNoReq() throws IOException { //Player can't pay the "ToPay" Possession
		Possession rewards = new Possession(1, 1, 1, 1, 1, 1, 1);
		Possession toPay1 = new Possession(1, 222, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 0, 1, 0, 0, 0, 0);
		Game testGame = new Game("Test");
		RmiClient rmiClient = new RmiClient(ViewType.CLI);
		RmiAdapter rmiAdapter = new RmiAdapter(rmiClient);
		rmiClient.setLog(System.out);
		ControllerManager controllerManager = new ControllerManager();
		RemoteView remoteView = new RemoteView(rmiAdapter, controllerManager);
		Convert testConvert = new Convert(testGame, rewards, toPay1, toTake1, new Possession(), new Possession(), 0);
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Player testPlayer2 = new Player("Test2", Color.Red, testGame);
		testGame.attachCurrent(remoteView);
		remoteView.setPlayer(testPlayer);
		testConvert.activateEffect(testPlayer, new CraftAction(testPlayer, CraftType.Harvest, 2));
		testPlayer2.getMyPersonalBoard().getMyPossession().add(rewards);
		assertTrue(testPlayer2.getMyPersonalBoard().getMyPossession().equals(testPlayer.getMyPersonalBoard().getMyPossession()));
	}
	
	
	
}
