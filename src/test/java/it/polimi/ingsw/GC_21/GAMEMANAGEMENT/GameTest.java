package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.MilitaryPoints;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.VictoryPoints;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class GameTest {

	@Test
	public void assignResourcesTest() {
		Game game = new Game("test");
		Player test1 = new Player("aaa", Color.Blue, game);
		Player test2 = new Player("aaa", Color.Green, game);
		Player test3 = new Player("aaa", Color.Yellow, game);
		game.assignResources();
		assertTrue(test1.getMyPersonalBoard().getMyPossession().equals(new Possession(5, 5, 5, 5, 0, 0, 0)));
		assertTrue(test2.getMyPersonalBoard().getMyPossession().equals(new Possession(6, 6, 6, 6, 0, 0, 0)));
		assertTrue(test3.getMyPersonalBoard().getMyPossession().equals(new Possession(7, 7, 7, 7, 0, 0, 0)));

	}
	
	@Test
	public void addPlayersTest(){
		Game game = new Game("test");
		Player test1 = new Player("aaa", Color.Blue, game); //method invoked into this constructor
		Player test2 = new Player("aaa", Color.Green, game);
		Player test3 = new Player("aaa", Color.Yellow, game);
		assertTrue(game.getPlayers().size()==3);
	}
	
	
	@Test
	public void generateRankingTest(){
		Game game = new Game("test");
		Player test1 = new Player("aaa", Color.Blue, game);
		Player test2 = new Player("aaa", Color.Green, game);
		Player test3 = new Player("aaa", Color.Yellow, game);
		for(int i = 0; i < game.getPlayers().size(); i++){
			game.getMilitaryPointsRanking().add(game.getPlayers().get(i));
			game.getVictoryPointsRanking().add(game.getPlayers().get(i));
		}
		test1.getMyPersonalBoard().getMyPossession().setVictoryPoints(new VictoryPoints(5));
		test2.getMyPersonalBoard().getMyPossession().setVictoryPoints(new VictoryPoints(8));
		test3.getMyPersonalBoard().getMyPossession().setVictoryPoints(new VictoryPoints(7));
		test1.getMyPersonalBoard().getMyPossession().setMilitaryPoints(new MilitaryPoints(5));
		test2.getMyPersonalBoard().getMyPossession().setMilitaryPoints(new MilitaryPoints(8));
		test3.getMyPersonalBoard().getMyPossession().setMilitaryPoints(new MilitaryPoints(7));

		game.generateRanking();
		assertTrue(game.getVictoryPointsRanking().get(0).equals(test2)); //Victory Points ranking test
		assertTrue(game.getVictoryPointsRanking().get(1).equals(test3));
		assertTrue(game.getVictoryPointsRanking().get(2).equals(test1));
		assertTrue(game.getMilitaryPointsRanking().get(0).equals(test2)); //Military Points ranking test
		assertTrue(game.getMilitaryPointsRanking().get(1).equals(test3));
		assertTrue(game.getMilitaryPointsRanking().get(2).equals(test1));

	}

}
