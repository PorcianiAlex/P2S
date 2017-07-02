package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.xml.bind.helpers.AbstractUnmarshallerImpl;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.MarketPlacement;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Privileges;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;
import it.polimi.ingsw.GC_21.VIEW.RmiAdapter;
import it.polimi.ingsw.GC_21.fx.ViewType;

public class EarningInfluencerTest {

	@Test
	public void testActivateEffectWithTowerPlacementAndFloorGivesBonus() throws IOException {
		Game testGame = new Game("Test");
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Possession malus = new Possession(1, 0, 0, 0, 0, 0, 0);
		EarningInfluencer testEffect = new EarningInfluencer(malus);
		DevelopmentCard testCard = new DevelopmentCard("aaa");
		testCard.setDevCardType(DevCardType.Building);
		Possession rewards = new Possession(31,2,0,0,0,0,0);
		Possession rewards1 = new Possession(15,2,0,0,0,0,0);
		testCard.setImmediateEffect(new Effect(rewards, 0, testGame));
		testGame.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getDevCardPlace().setCard(testCard);
		testGame.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getSingleActionSpace().setActionSpaceEffect(new Effect(rewards1, 0, testGame));
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, testGame.getBoard());
		testEffect.activateEffect(testPlayer, testAction);
		Possession expected1 = new Possession(30, 2, 0, 0, 0, 0, 0);
		Possession actual1 = testCard.getImmediateEffect().getRewards();
		Possession expected2 = new Possession(14,2,0,0,0,0,0);
		Possession actual2 = testGame.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getSingleActionSpace().getActionSpaceEffect().getRewards();
	}
	
	@Test
	public void testActivateEffectWithTowerPlacement() throws IOException {
		Game testGame = new Game("Test");
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Possession malus = new Possession(1, 0, 0, 0, 0, 0, 0);
		EarningInfluencer testEffect = new EarningInfluencer(malus);
		DevelopmentCard testCard = new DevelopmentCard("aaa");
		testCard.setDevCardType(DevCardType.Building);
		Possession rewards = new Possession(1,2,0,0,0,0,0);
		testCard.setImmediateEffect(new Effect(rewards, 0, testGame));
		testGame.getBoard().getSpecificTower(DevCardType.Building).getFloors()[0].getDevCardPlace().setCard(testCard);
		TowerPlacement testAction = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, testGame.getBoard());
		testEffect.activateEffect(testPlayer, testAction);
		Possession expected = new Possession(0, 2, 0, 0, 0, 0, 0);
		Possession actual = testCard.getImmediateEffect().getRewards();
		assertTrue(actual.equals(expected));
	}
	
	@Test
	public void testActivateEffectWithMarketPlacement() throws IOException {
		Game testGame = new Game("Test");		
		Player testPlayer = new Player("Test", Color.Blue, testGame);
		Possession malus = new Possession(4, 0, 0, 0, 0, 0, 0);
		EarningInfluencer testEffect = new EarningInfluencer(malus);
		MarketPlacement testAction = MarketPlacement.factoryMarketPlacement(testPlayer, FamilyMemberColor.Black, 1, 2, testGame.getBoard());
		testEffect.activateEffect(testPlayer, testAction);
		Possession actual = testAction.getSelectedActionSpace().getActionSpaceEffect().getRewards();
		Possession expected = new Possession(1, 0, 0, 0, 0, 0, 0);
		System.out.println(actual);
		assertTrue(actual.equals(expected));
	}

}
