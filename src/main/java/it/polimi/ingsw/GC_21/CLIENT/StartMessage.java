package it.polimi.ingsw.GC_21.CLIENT;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.view.InputForm;
import it.polimi.ingsw.GC_21.view.LoginInput;

public class StartMessage extends MessageToClient{
	
	
	public StartMessage() {
		super(true, "Start Game");
	}

	@Override
	public InputForm executeCLI(Scanner keyboard) {
		
		try {
			LoginInput loginInput = new LoginInput();
			loginInput.chooseUsername(keyboard);
			return loginInput;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
