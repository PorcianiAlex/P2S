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
	private TimerThread timerThread;
	private Connections client;
	
	public Connections getClient() {
		return client;
	}

	public void setClient(Connections client) {
		this.client = client;
	}

	public ChooseActionMessage(boolean result, String description, Player player) {
		super(result, true, description);
		this.player = player;
		timerThread = new TimerThread();
	}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		System.out.println("sononellaexcli");
		timerThread.setClient(client);
		timerThread.start();
		super.executeCLI(keyboard);
		ActionInput actionInput = new ActionInput();
		try {
			System.out.println("sto per chiamare la ca");
			return actionInput.chooseAction(keyboard, player);
		} catch (ExecutionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actionInput;
		}
	

	@Override
	public void executeGUI(FXMLGameController gameController) {
		gameController.ifChooseAction(result, description, player);
		
	}

	
}
