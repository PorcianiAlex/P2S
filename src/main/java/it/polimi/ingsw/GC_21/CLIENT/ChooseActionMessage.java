package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.ActionInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LobbyInput;

public class ChooseActionMessage extends MessageToClient {

	public ChooseActionMessage(String description) {
		super(true, description);
	}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		super.executeCLI(keyboard);
		ActionInput actionInput = new ActionInput();
		actionInput.inputFromCli(keyboard);
		return actionInput;
	}

}
