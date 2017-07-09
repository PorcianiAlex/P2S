package it.polimi.ingsw.GC_21.CLIENT;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.GameEndState;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class GameOverMessage extends MessageToClient{
	private Board board;
	private ArrayList<Player> players;
	private ArrayList<Player> victoryPointsRanking;
	
	public GameOverMessage(boolean result, String description, Board board, ArrayList<Player> players,
			ArrayList<Player> victoryPointsRanking) {
		super(result, false, description);
		this.board = board;
		this.players = players;
		this.victoryPointsRanking = victoryPointsRanking;
		this.gameEndState = GameEndState.Over;
	}
	
	public void executeGUI(FXMLGameController gameController) {		
		gameController.gameOver(description, victoryPointsRanking);
	}


}
