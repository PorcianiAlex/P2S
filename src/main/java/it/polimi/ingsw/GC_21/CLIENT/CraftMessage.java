package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.VIEW.CraftInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;

public class CraftMessage extends MessageToClient {
	private CraftType craftType;
	private int actionValue;

	public CraftMessage(CraftType craftType, int actionValue) {
		super(true, "You can make a craft with value " + actionValue + ", how many servant do you want to convert?");
		this.craftType = craftType;
		this.actionValue = actionValue;
		}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		CraftInput craftInput = new CraftInput(craftType, actionValue);
		craftInput.inputFromCli(keyboard);
		return craftInput;
	}

}
