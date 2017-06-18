package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.CreatePlayerInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;

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
