package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;

import org.junit.experimental.theories.Theories;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.CONTROLLER.Controller;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.MilitaryPoints;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.VictoryPoints;
import it.polimi.ingsw.GC_21.PLAYER.Player;

import it.polimi.ingsw.GC_21.UTILITIES.Observable;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;

public class Game extends Observable implements Serializable {
	
	private static int currentNumberOfGame = 0; 
	private int id;
	private String host;
	private int numberOfPlayers;
	private Board board;
	private ArrayList<Player> players;
	private Age currentAge;
	private ExcommHandler excommHandler;
	private LeaderDeck leaderDeck;
	private ArrayList<Player> victoryPointsRanking;
	private ArrayList<Player> militaryPointsRanking;
	
	
	public Game(String host) {
		this.id = currentNumberOfGame + 1;
		currentNumberOfGame++;
		this.board = new Board(this);	
		this.players = new ArrayList<Player>();
		this.excommHandler = new ExcommHandler(this);
		this.numberOfPlayers = 1;
		this.host = host;
		this.leaderDeck = new LeaderDeck(this);
		victoryPointsRanking = new ArrayList<Player>();
		militaryPointsRanking = new ArrayList<Player>();
		}
	
	


	public ArrayList<Player> getVictoryPointsRanking() {
		return victoryPointsRanking;
	}




	public void setVictoryPointsRanking(ArrayList<Player> victoryPointsRanking) {
		this.victoryPointsRanking = victoryPointsRanking;
	}




	public ArrayList<Player> getMilitaryPointsRanking() {
		return militaryPointsRanking;
	}




	public void setMilitaryPointsRanking(ArrayList<Player> militaryPointsRanking) {
		this.militaryPointsRanking = militaryPointsRanking;
	}




	public LeaderDeck getLeaderDeck() {
		return leaderDeck;
	}


	public void setLeaderDeck(LeaderDeck leaderDeck) {
		this.leaderDeck = leaderDeck;
	}


	public Game(int id, String host, int numberOfPlayers, Board board, ArrayList<Player> players, Age currentAge,
			ExcommHandler excommHandler, LeaderDeck leaderDeck) {
		super();
		this.id = id;
		this.host = host;
		this.numberOfPlayers = numberOfPlayers;
		this.board = board;
		this.players = players;
		this.currentAge = currentAge;
		this.excommHandler = excommHandler;
		this.leaderDeck = new LeaderDeck(this);
		victoryPointsRanking = new ArrayList<Player>();
		militaryPointsRanking = new ArrayList<Player>();
	}


	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public ExcommHandler getExcommHandler() {
		return excommHandler;
	}

	public void setExcommHandler(ExcommHandler excommHandler) {
		this.excommHandler = excommHandler;
	}

	
	
	public void executeGame() {
		for(int i = 0; i < players.size(); i++){
			victoryPointsRanking.add(players.get(i));
			militaryPointsRanking.add(players.get(i));
		}
		for (int i = 1; i < 4; i++) {
			currentAge = new Age(i, this);
			currentAge.executeAge();
		}
		
	}


	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public static int getCurrentNumberOfGame() {
		return currentNumberOfGame;
	}

	public static void setCurrentNumberOfGame(int currentNumberOfGame) {
		Game.currentNumberOfGame = currentNumberOfGame;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void addPlayers(Player player) {
		this.players.add(player);
	}

	public Age getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(Age currentAge) {
		this.currentAge = currentAge;
	}
	

	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String toString() {
		String string = new String("game: " + id + " players: " + numberOfPlayers + " host: " + host + "\n");
		return string;
	}



	public void generateRanking() {
		for (int i = 0; i < victoryPointsRanking.size(); i++) {
			for (int j = i; j < victoryPointsRanking.size(); j++){
				int victoryPointsI = victoryPointsRanking.get(i).getMyPersonalBoard().getMyPossession().getVictoryPoints().getValue();
				int victoryPointsJ = victoryPointsRanking.get(j).getMyPersonalBoard().getMyPossession().getVictoryPoints().getValue();
				if (victoryPointsI < victoryPointsJ){
					Player tmp = victoryPointsRanking.get(i);
					victoryPointsRanking.set(i, victoryPointsRanking.get(j));
					victoryPointsRanking.set(j, tmp);
				}
			}
		}
		for (int i = 0; i < militaryPointsRanking.size(); i++) {
			for (int j = i; j < militaryPointsRanking.size(); j++){
				int militaryPointsI = militaryPointsRanking.get(i).getMyPersonalBoard().getMyPossession().getMilitaryPoints().getValue();
				int militaryPointsJ = militaryPointsRanking.get(j).getMyPersonalBoard().getMyPossession().getMilitaryPoints().getValue();
				if (militaryPointsI < militaryPointsJ){
					Player tmp = militaryPointsRanking.get(i);
					militaryPointsRanking.set(i, militaryPointsRanking.get(j));
					militaryPointsRanking.set(j, tmp);
				}
			}
		}
		
	}
	
	
}