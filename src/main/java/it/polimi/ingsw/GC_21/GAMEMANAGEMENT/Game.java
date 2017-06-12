package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.Serializable;
import java.util.ArrayList;


import org.junit.experimental.theories.Theories;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.PLAYER.Player;

import it.polimi.ingsw.GC_21.UTILITIES.Observable;
import it.polimi.ingsw.GC_21.controller.Controller;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Game extends Observable implements Serializable {
	
	private static int currentNumberOfGame = 0; 
	private int id;
	private int numberOfPlayers;
	private Board board;
	private ArrayList<Player> players;
	private Age currentAge;
	private ExcommHandler excommHandler;
	
	
	
	

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

	public Game() {
		this.id = currentNumberOfGame + 1;
		currentNumberOfGame++;
		this.board = new Board(this);	
		this.players = new ArrayList<Player>();
		this.excommHandler = new ExcommHandler(this);
		}
	
	public void executeGame() {
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

	public String toString() {
		String string = new String("game: " + id + " players: " + players.size() + " host: " + players.get(0).getName() + "\n");
		return string;
	}
	
	
}