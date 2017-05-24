package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import it.polimi.ingsw.GC_21.BOARD.Board;

public class Game {

	private int id;
	private int numberOfPlayers;
	private Board board;

	public void preparegame() {
		// TODO - implement Game.preparegame
		throw new UnsupportedOperationException();
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