package it.polimi.ingsw.GC_21.CLIENT;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Floor;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.PassInput;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class TurnMessage extends MessageToClient{
	private int age;
	private Board board;
	private ArrayList<Player> players;
	private boolean outOfTime;
	private int round;
	private int turnNumber;
	private SingleActionSpace singleActionSpace;
	private Floor floor;
	private Tower tower;




	public TurnMessage(Board board, ArrayList<Player> players, int age, int round, int turnNumber, String description) {
		super(true, false, description);
		this.board = board;
		this.players = players;
		this.age = age;
		this.round = round;
		this.turnNumber = turnNumber;
		this.tower = board.getSpecificTower(DevCardType.Territory);
		this.floor = board.getSpecificTower(DevCardType.Territory).getFloors()[0];
		this.singleActionSpace = board.getSpecificTower(DevCardType.Territory).getFloors()[0].getSingleActionSpace();
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
		super.executeCLI(keyboard);
		System.out.println(board.toString());
		return null;
	}
	
	@Override
	public void executeGUI(FXMLGameController gameController) {
		gameController.refreshBoard(board, players, description);
		
	}
	
}
