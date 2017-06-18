package it.polimi.ingsw.GC_21.EFFECT;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementInfluencerTest {

	@Test
	public void testActivateEffectWithNeutral() {
		ArrayList <FamilyMemberColor> familyMemberColors = new ArrayList<FamilyMemberColor>();
		familyMemberColors.add(FamilyMemberColor.Black);
		familyMemberColors.add(FamilyMemberColor.Neutral);
		PlacementInfluencer testPlacementInfluencer = new PlacementInfluencer(2, DevCardType.Building, new Possession(), familyMemberColors);
		Game game = new Game("");
		Player testPlayer = new Player("ale", Color.Blue, game);
		game.addPlayers(testPlayer);
		TowerPlacement testTowerPlacement = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testPlacementInfluencer.activateEffect(testPlayer, testTowerPlacement);
		int actual = testTowerPlacement.getActionValue();
		int expected = testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getAssociatedDice().getValue() + 2;
		assertTrue(actual==expected);
	}
	
	@Test
	public void testActivateEffectWithBlack() {
		PlacementInfluencer testPlacementInfluencer = new PlacementInfluencer(2, DevCardType.Territory, new Possession());
		Game game = new Game("");
		Player testPlayer = new Player("ale", Color.Blue, game);
		game.addPlayers(testPlayer);
		TowerPlacement testTowerPlacement = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testPlacementInfluencer.activateEffect(testPlayer, testTowerPlacement);
		int actual = testTowerPlacement.getActionValue();
		int expected = testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getAssociatedDice().getValue() + 2;
		assertFalse(actual==expected);
	}
	
	@Test
	public void testActivateEffect() {
		PlacementInfluencer testPlacementInfluencer = new PlacementInfluencer(2, DevCardType.Territory, new Possession());
		Game game = new Game("");
		Player testPlayer = new Player("ale", Color.Blue, game);
		game.addPlayers(testPlayer);
		TowerPlacement testTowerPlacement = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testPlacementInfluencer.activateEffect(testPlayer, testTowerPlacement);
		int actual = testTowerPlacement.getActionValue();
		int expected = testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getAssociatedDice().getValue() + 2;
		assertFalse(actual==expected);
	}
	
	
	@Test
	public void testActivateEffect3() {
		PlacementInfluencer testPlacementInfluencer = new PlacementInfluencer(2, DevCardType.Building, new Possession());
		Game game = new Game("");
		Player testPlayer = new Player("ale", Color.Blue, game);
		game.addPlayers(testPlayer);
		testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).setActionValue(3);
		TowerPlacement testTowerPlacement = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testPlacementInfluencer.activateEffect(testPlayer, testTowerPlacement);
		int actual = testTowerPlacement.getActionValue();
		System.out.println(actual);
		int expected = testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getActionValue() + 2;
		System.out.println(expected);
		assertTrue(actual==expected);
	}
	
	@Test
	public void testActivateEffect2() {
		PlacementInfluencer testPlacementInfluencer = new PlacementInfluencer(1, DevCardType.Building, new Possession());
		Game game = new Game("");
		Player testPlayer = new Player("ale", Color.Blue, game);
		game.addPlayers(testPlayer);
		testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).setActionValue(3);
		TowerPlacement testTowerPlacement = TowerPlacement.factoryTowerPlacement(testPlayer, FamilyMemberColor.Black, DevCardType.Building, 1, 1, game.getBoard());
		testPlacementInfluencer.activateEffect(testPlayer, testTowerPlacement);
		int actual = testTowerPlacement.getActionValue();
		int expected = testPlayer.getSpecificFamilyMember(FamilyMemberColor.Black).getActionValue() + 1;
		assertTrue(actual==expected);
	}

}
