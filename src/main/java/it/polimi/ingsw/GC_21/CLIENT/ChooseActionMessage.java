package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.ExecutionException;

import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.ActionInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class ChooseActionMessage extends MessageToClient {
	private Player player;
	
	public ChooseActionMessage(boolean result, String description, Player player) {
		super(result, true, description);
		this.player = player;
	}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		super.executeCLI(keyboard);
		//Timer timer = startTimer();
		ActionInput actionInput = new ActionInput();
		//timer.cancel();
		try {
			inputFormToSend = actionInput.chooseAction(keyboard, player);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputFormToSend;
	}

	@Override
	public void executeGUI(FXMLGameController gameController) {
		gameController.ifChooseAction(result, description);
		
	}
	
}
