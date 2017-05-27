package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.junit.experimental.theories.Theories;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.UTILITIES.MyObserver;
import it.polimi.ingsw.GC_21.UTILITIES.ViewObserver;
import it.polimi.ingsw.GC_21.controller.Controller;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Game {
	
	private Controller controller;
	private static int currentNumberOfGame = 0; 
	private int id;
	private int numberOfPlayers;
	private Board board;
	private ArrayList<Player> players;
	private Age currentAge;
	private ViewObserver viewObserver;
	

	public Game() {
		this.id = currentNumberOfGame + 1;
		currentNumberOfGame++;
		this.board = new Board();	
		this.players = new ArrayList<Player>();
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

	public ViewObserver getViewObserver() {
		return viewObserver;
	}

	public void setMyObserver(ViewObserver viewObserver) {
		this.viewObserver = viewObserver;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
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
	
	
	public void setViewObserver(ViewObserver view) {
		this.viewObserver = view;
	}
	
	
}