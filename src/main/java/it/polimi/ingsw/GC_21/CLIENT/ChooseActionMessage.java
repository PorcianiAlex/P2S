package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.ActionInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ChooseActionMessage extends MessageToClient {
	private Board board;
	private Player player;

	public ChooseActionMessage(String description, Board board, Player player) {
		super(true, description);
		this.board = board;
		this.player = player;
	}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		super.executeCLI(keyboard);
		ActionInput actionInput = new ActionInput();
		return actionInput.chooseAction(keyboard, player);
	}

}
