package it.polimi.ingsw.GC_21.CLIENT;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.PassInput;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class TurnMessage extends MessageToClient{
	private Board board;
	private ArrayList<Player> players;
	private boolean outOfTime;


	public TurnMessage(Board board, ArrayList<Player> players) {
		super(true, "");
		this.board = board;
		this.players = players;
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
	public InputForm executeCLI(Scanner keyboard) {
		startTimer();
		return null;
	}
	
	@Override
	public void executeGUI(FXMLGameController gameController) {
		gameController.refreshBoard(board, players);
		
	}
	
}
