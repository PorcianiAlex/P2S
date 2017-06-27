package it.polimi.ingsw.GC_21.CLIENT;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.Board;

import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class TurnMessage extends MessageToClient{
	private int age;
	private Board board;
	private ArrayList<Player> players;
	private int round;
	private int turnNumber;
	


	public TurnMessage(Board board, ArrayList<Player> players, int age, int round, int turnNumber, String description) {
		super(true, false, description);
		this.board = board;
		this.players = players;
		this.age = age;
		this.round = round;
		this.turnNumber = turnNumber;
		
	}


	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	

	@Override
	public InputForm executeCLI(Object LOCK) throws InterruptedException {
		System.out.println(description);;
		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).getMyPersonalBoard().toString() + " \n" );
		}
		System.out.println(board.toString());
		return null;
	}
	
	@Override
	public void executeGUI(FXMLGameController gameController) {
		gameController.refreshBoard(board, players, description);
		
	}
	
}
