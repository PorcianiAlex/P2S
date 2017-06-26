package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;


import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LoginInput;

public class StartMessage extends MessageToClient{
	
	
	public StartMessage() {
		super(true, false, "Start Game");
	}

	@Override
	public InputForm executeCLI(Scanner keyboard) {
			LoginInput loginInput = new LoginInput();
			loginInput.inputFromCli(keyboard);
			return loginInput;
	}

}
