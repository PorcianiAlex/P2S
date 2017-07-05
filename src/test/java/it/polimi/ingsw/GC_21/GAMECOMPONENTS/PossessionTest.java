package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PossessionTest {

	@Test
	public void testCompare() {
		Possession test = new Possession(5,5,5,5,0,0,0);
		Possession test2 = new Possession(6,5,5,5,0,0,0);
		boolean resultOfComparison = test2.compare(test);
		assertTrue(resultOfComparison);
	}

	@Test
	public void testCheckRequirements() {
		Possession test = new Possession(5,5,5,5,0,0,0);
		Possession test2 = new Possession(6,5,5,5,0,0,0);
		Player player = new Player("aaa", Color.Blue, new Game("aaa"));
		player.getMyPersonalBoard().setMyPossession(test2);
		boolean resultOfComparison = test.checkRequirements(player);
		assertTrue(resultOfComparison);
	}

	@Test
	public void testMultiplyResource() {
		Possession test = new Possession(5,5,5,5,0,0,0);
		test.multiplyResource(3);
		assertTrue(test.equals(new Possession(15, 15, 15, 15, 0, 0, 0)));
	}
	
	@Test
	public void testAdd(){
		Possession test = new Possession(5,5,5,5,0,0,0);
		Possession test2 = new Possession(6,5,5,5,0,0,0);
		test.add(test2);
		assertTrue(test.equals(new Possession(11, 10, 10, 10, 0, 0, 0)));
	}
	
	@Test
	public void testsubtract(){
		Possession test = new Possession(5,5,5,5,0,0,0);
		Possession test2 = new Possession(6,5,5,5,0,0,0);
		test2.subtract(test);
		assertTrue(test2.equals(new Possession(1, 0, 0, 0, 0, 0, 0)));
	}
	
	
}
