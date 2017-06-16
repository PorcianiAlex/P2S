package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.view.InputForm;

public class CheckColorMessage extends MessageToClient {

	public CheckColorMessage(boolean result, String description) {
		super(result, description);
	}
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		return super.executeCLI(keyboard);
	}

}
