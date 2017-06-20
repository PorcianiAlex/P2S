package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;
import java.util.Timer;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.ActionInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class ChooseActionMessage extends MessageToClient {
	private Player player;
	
	public ChooseActionMessage(String description, Board board, Player player) {
		super(true, description);
		this.player = player;
	}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		super.executeCLI(keyboard);
		//Timer timer = startTimer();
		ActionInput actionInput = new ActionInput();
		//timer.cancel();
		inputFormToSend = actionInput.chooseAction(keyboard, player);
		return inputFormToSend;
	}

	@Override
	public void executeGUI(FXMLGameController gameController) {
		gameController.ifChooseAction();
		
	}
	
}
