package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.view.LoginInput;

public class StartMessage extends Message{
	
	
	@Override
	public boolean convert() {
		
		try {
			LoginInput loginInput = new LoginInput();
			loginInput.chooseUsername();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

}
