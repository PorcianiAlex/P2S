package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.view.ActionInput;
import it.polimi.ingsw.GC_21.view.InputForm;
import it.polimi.ingsw.GC_21.view.LobbyInput;

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
