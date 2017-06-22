package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.ExcommInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;

public class ExcommMessage extends MessageToClient {

	public ExcommMessage() {
		super(true, true, "Bergoglio wants to know if you have been a great guy recently! \n"
				+ "Be careful: if you disappoint him, you will get a permanent malus!!!" + 
				"\nDo you want to be excommunicated? (Y) - (N)");
	}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		System.out.println(description);
		ExcommInput excommInput = new ExcommInput();
		excommInput.inputFromCli(keyboard);
		return excommInput;

	}

}
