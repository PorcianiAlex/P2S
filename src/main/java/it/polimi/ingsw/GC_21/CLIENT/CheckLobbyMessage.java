package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.view.CreatePlayerInput;
import it.polimi.ingsw.GC_21.view.InputForm;
import it.polimi.ingsw.GC_21.view.LobbyInput;

public class CheckLobbyMessage extends MessageToClient{

	public CheckLobbyMessage(boolean result, String description) {
		super(result, description);
	}
	
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		super.executeCLI(keyboard);
		CreatePlayerInput createPlayerInput = new CreatePlayerInput();
		createPlayerInput.inputFromCli(keyboard);
		return createPlayerInput;
	}

}
