package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.util.ArrayList;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class Game {
	private static int currentNumberOfGame = 0; 
	private int id;
	private int numberOfPlayers;
	private Board board;
	private ArrayList<Player> players;	
	

	public Game(int numberOfPlayers, ArrayList<Player> players) {
		super();
		this.id = currentNumberOfGame + 1;
		currentNumberOfGame++;
		this.numberOfPlayers = numberOfPlayers;
		this.board = new Board();
		this.players = players;
		DevDeck VentureDeck = new DevDeck(DevCardType.Venture, 1);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}