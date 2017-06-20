package it.polimi.ingsw.GC_21.CLIENT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.LoginInput;

public class StartMessage extends MessageToClient{
	
	
	public StartMessage() {
		super(true, "Start Game");
	}

	@Override
	public InputForm executeCLI(Scanner keyboard) {
			System.out.println("In start message");
			LoginInput loginInput = new LoginInput();
			loginInput.inputFromCli(keyboard);
			return loginInput;
	}

}
