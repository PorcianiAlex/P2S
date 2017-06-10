package it.polimi.ingsw.GC_21.PLAYER;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.Convert;
import it.polimi.ingsw.GC_21.EFFECT.CraftInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.ForEachGet;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.EFFECT.PlacementInfluencer;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.CraftCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.PossesionCreator;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PersonalBoardTest {
	
	/*@Test
	public void testAddPermanent(){
		Game game = new Game();
		Player testPlayer = new Player("aaa", Color.Blue, game);
		PlacementInfluencer test = new PlacementInfluencer(1, DevCardType.Building, new Possession());
		PlacementInfluencer test2 = new PlacementInfluencer(1, DevCardType.Building, new Possession());
		CraftInfluencer test3 = new CraftInfluencer(CraftType.Harvest, 3);
		testPlayer.getMyPersonalBoard().addPermanentEffect(test);
		testPlayer.getMyPersonalBoard().addPermanentEffect(test2);
		testPlayer.getMyPersonalBoard().addPermanentEffect(test3);
		assertTrue(2==testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().size());
		assertTrue(1==testPlayer.getMyPersonalBoard().getToCallBeforeCraftEffects().size());
	}
	
	@Test
	public void testAddPermanent2(){
		Game game = new Game();
		Player testPlayer = new Player("aaa", Color.Blue, game);
		PlacementInfluencer test = new PlacementInfluencer(1, DevCardType.Building, new Possession());
		PlacementInfluencer test2 = new PlacementInfluencer(1, DevCardType.Building, new Possession());
		testPlayer.getMyPersonalBoard().addPermanentEffect(test);
		testPlayer.getMyPersonalBoard().addPermanentEffect(test2);
		assertTrue(2==testPlayer.getMyPersonalBoard().getToCallBeforePlacementEffects().size());
	}

	@Test
	public void testAddDevCard() {
		DevelopmentCard testDevCard = new DevelopmentCard("test1");
		testDevCard.setDevCardType(DevCardType.Building);
		testDevCard.setImmediateEffect(new Immediate(new Possession(), 0));
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 2, 0, 0, 0, 0, 0);
		Possession toPay2 = new Possession(0, 0, 0, 0, 0, 0, 0);
		Possession toTake2 = new Possession(0, 0, 0, 0, 0, 0, 0);
		testDevCard.setSecondaryEffect(new Convert(null, toPay1, toTake1, toPay2, toTake2, 0));
		Game game = new Game();
		Player testPlayer = new Player("ale", Color.Blue, game);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard);
		assertTrue(testPlayer.getMyPersonalBoard().getSpecificOwnedCards(DevCardType.Building).getMyOwnedCards()[0].getCard().getName().equals(testDevCard.getName()));
	}

	@Test
	public void testPayPossession() {
		Possession testPossession = new Possession(1,1,1,1,1,1,1);
		Game game = new Game();
		Player testPlayer = new Player("ale", Color.Blue, game);
		testPlayer.getMyPersonalBoard().payPossession(testPossession);
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(4, 4, 4, 4, 4, 4, 4);
		assertTrue(actual.equals(expected));

	}

	@Test
	public void testActivateCraftProduction() {
		Game game = new Game();
		Player testPlayer = new Player("ale", Color.Blue, game);
		//FIRST CARD CONSTRUCTION
		CraftCard testDevCard = new CraftCard("test1");
		testDevCard.setDevCardType(DevCardType.Building);
		testDevCard.setImmediateEffect(new Immediate(new Possession(), 0));
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 2, 0, 0, 0, 0, 0);
		testDevCard.setRequiredValueForCraft(5);
		testDevCard.setSecondaryEffect(new Convert(null, toPay1, toTake1, new Possession(), new Possession(), 0));
		//SECOND CARD CONSTRUCTION
		CraftCard testDevCard2 = new CraftCard("test2");
		testDevCard2.setDevCardType(DevCardType.Building);
		testDevCard2.setImmediateEffect(new Immediate(new Possession(), 0));
		Possession toPay12 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toTake12 = new Possession(0, 2, 0, 0, 0, 0, 0);
		testDevCard2.setSecondaryEffect(new Convert(null, toPay12, toTake12, new Possession(), new Possession(), 0));
		testDevCard2.setRequiredValueForCraft(5);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard2);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard);
		testPlayer.getMyPersonalBoard().activateCraft(CraftType.Production, 6);
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(3,9,5,5,5,5,5);
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testActivateCraftHarvest() {
		Game game = new Game();
		Player testPlayer = new Player("ale", Color.Blue, game);
		//FIRST CARD CONSTRUCTION
		CraftCard testDevCard = new CraftCard("test1");
		testDevCard.setDevCardType(DevCardType.Territory);
		testDevCard.setImmediateEffect(new Immediate(new Possession(), 0));
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 2, 0, 0, 0, 0, 0);
		testDevCard.setRequiredValueForCraft(5);
		testDevCard.setSecondaryEffect(new Convert(null, toPay1, toTake1, new Possession(), new Possession(), 0));
		//SECOND CARD CONSTRUCTION
		CraftCard testDevCard2 = new CraftCard("test2");
		testDevCard2.setDevCardType(DevCardType.Territory);
		testDevCard2.setImmediateEffect(new Immediate(new Possession(), 0));
		Possession toPay12 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toTake12 = new Possession(0, 2, 0, 0, 0, 0, 0);
		testDevCard2.setSecondaryEffect(new Convert(null, toPay12, toTake12, new Possession(), new Possession(), 0));
		testDevCard2.setRequiredValueForCraft(5);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard2);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard);
		testPlayer.getMyPersonalBoard().activateCraft(CraftType.Harvest, 6);
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(3,9,5,5,5,5,5);
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testActivateCraftForEachGetProductionCraftValue3() {
		Game game = new Game();
		Player testPlayer = new Player("ale", Color.Blue, game);
		//FIRST CARD CONSTRUCTION
		CraftCard testDevCard = new CraftCard("test1");
		testDevCard.setDevCardType(DevCardType.Building);
		testDevCard.setImmediateEffect(new Immediate(new Possession(), 0));
		testDevCard.setRequiredValueForCraft(5);
		testDevCard.setSecondaryEffect(new ForEachGet(new Possession(), 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false));
		//SECOND CARD CONSTRUCTION
		CraftCard testDevCard2 = new CraftCard("test2");
		testDevCard2.setDevCardType(DevCardType.Building);
		testDevCard2.setImmediateEffect(new Immediate(new Possession(), 0));
		testDevCard2.setRequiredValueForCraft(2);
		testDevCard2.setSecondaryEffect(new ForEachGet(new Possession(), 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false));		
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard2);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard);
		testPlayer.getMyPersonalBoard().activateCraft(CraftType.Production, 4);
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(5,10,5,5,5,5,5);
		System.out.println(actual.toString());
		assertTrue(expected.equals(actual));
	}
	
	@Test
	public void testActivateCraftForEachGetProductionFails() {
		Game game = new Game();
		Player testPlayer = new Player("ale", Color.Blue, game);
		//FIRST CARD CONSTRUCTION
		CraftCard testDevCard = new CraftCard("test1");
		testDevCard.setDevCardType(DevCardType.Building);
		testDevCard.setImmediateEffect(new Immediate(new Possession(), 0));
		testDevCard.setRequiredValueForCraft(5);
		testDevCard.setSecondaryEffect(new ForEachGet(new Possession(), 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false));
		//SECOND CARD CONSTRUCTION
		CraftCard testDevCard2 = new CraftCard("test2");
		testDevCard2.setDevCardType(DevCardType.Territory);
		testDevCard2.setImmediateEffect(new Immediate(new Possession(), 0));
		testDevCard2.setSecondaryEffect(new ForEachGet(new Possession(), 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false));		testDevCard2.setRequiredValueForCraft(5);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard2);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard);
		testPlayer.getMyPersonalBoard().activateCraft(CraftType.Production, 3);
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(5,5,5,5,5,5,5);
		assertTrue(expected.equals(actual));

	}
	
	@Test
	public void testActivateCraftForEachGetProduction() {
		Game game = new Game();
		Player testPlayer = new Player("ale", Color.Blue, game);
		//FIRST CARD CONSTRUCTION
		CraftCard testDevCard = new CraftCard("test1");
		testDevCard.setDevCardType(DevCardType.Building);
		testDevCard.setImmediateEffect(new Immediate(new Possession(), 0));
		testDevCard.setRequiredValueForCraft(5);
		testDevCard.setSecondaryEffect(new ForEachGet(new Possession(), 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false));
		//SECOND CARD CONSTRUCTION
		CraftCard testDevCard2 = new CraftCard("test2");
		testDevCard2.setDevCardType(DevCardType.Territory);
		testDevCard2.setImmediateEffect(new Immediate(new Possession(), 0));
		testDevCard2.setSecondaryEffect(new ForEachGet(new Possession(), 0, null, 0, ResourceType.Coins, 1, ResourceType.Woods, 1, false));		testDevCard2.setRequiredValueForCraft(5);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard2);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard);
		testPlayer.getMyPersonalBoard().activateCraft(CraftType.Production, 6);
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(5,10,5,5,5,5,5);
		assertTrue(expected.equals(actual));

	}

	
	@Test
	public void testActivateCraftOnlyHarvest() {
		Game game = new Game();
		Player testPlayer = new Player("ale", Color.Blue, game);
		//FIRST CARD CONSTRUCTION
		CraftCard testDevCard = new CraftCard("test1");
		testDevCard.setDevCardType(DevCardType.Building);
		testDevCard.setImmediateEffect(new Immediate(new Possession(), 0));
		Possession toPay1 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toTake1 = new Possession(0, 2, 0, 0, 0, 0, 0);
		testDevCard.setRequiredValueForCraft(5);
		testDevCard.setSecondaryEffect(new Convert(null, toPay1, toTake1, new Possession(), new Possession(), 0));
		//SECOND CARD CONSTRUCTION
		CraftCard testDevCard2 = new CraftCard("test2");
		testDevCard2.setDevCardType(DevCardType.Territory);
		testDevCard2.setImmediateEffect(new Immediate(new Possession(), 0));
		Possession toPay12 = new Possession(1, 0, 0, 0, 0, 0, 0);
		Possession toTake12 = new Possession(0, 2, 0, 0, 0, 0, 0);
		testDevCard2.setSecondaryEffect(new Convert(null, toPay12, toTake12, new Possession(), new Possession(), 0));
		testDevCard2.setRequiredValueForCraft(5);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard2);
		testPlayer.getMyPersonalBoard().addDevCard(testDevCard);
		testPlayer.getMyPersonalBoard().activateCraft(CraftType.Production, 6);
		Possession actual = testPlayer.getMyPersonalBoard().getMyPossession();
		Possession expected = new Possession(4,7,5,5,5,5,5);
		assertTrue(expected.equals(actual));

	}
*/
}
