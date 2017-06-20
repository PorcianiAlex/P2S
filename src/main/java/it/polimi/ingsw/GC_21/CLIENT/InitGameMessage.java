package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InitGameInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;

public class InitGameMessage extends MessageToClient{

	public InitGameMessage(String description) {
		super(true, "Write 'start' when you want to start the game! \nYou must be 2 at least");
	}
	
	
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		InitGameInput initGameInput =new InitGameInput();
		initGameInput.inputFromCli(keyboard);
		return inputFormToSend;
	}

}
