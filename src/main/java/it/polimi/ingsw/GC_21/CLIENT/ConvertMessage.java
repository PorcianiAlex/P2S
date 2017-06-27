package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.VIEW.ConvertInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;

public class ConvertMessage extends MessageToClient {
	private Possession toPay1;
	private Possession toTake1;
	private Possession toPay2;
	private Possession toTake2;
	
	
	public ConvertMessage(Possession toPay1, Possession toTake1, Possession toPay2, Possession toTake2) {
		super(true, true, "You can choose between two conversion!\n(1) or (2)?"
				+ "If you pay: " + toPay1.toString() + ", you'll get: " + toTake1.toString()
				+ "If you pay: " + toPay2.toString() + ", you'll get: " + toTake2.toString());
		this.toPay1 = toPay1;
		this.toTake1 = toTake1;
		this.toPay2 = toPay2;
		this.toTake2 = toTake2;
	}	
	
	
	@Override
	public InputForm executeCLI(Object lOCK) throws InterruptedException {
		inputForm = new ConvertInput(toPay1, toTake1, toPay2, toTake2);
		return super.executeCLI(lOCK);
	}
	
	
}
