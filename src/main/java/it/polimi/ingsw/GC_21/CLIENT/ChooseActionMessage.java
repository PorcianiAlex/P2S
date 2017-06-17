package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.view.ActionInput;
import it.polimi.ingsw.GC_21.view.InputForm;
import it.polimi.ingsw.GC_21.view.LobbyInput;

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
