package it.polimi.ingsw.GC_21.VIEW;

import java.io.CharConversionException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.CONTROLLER.ConvertController;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class ConvertInput extends InputForm {
	private Possession toPay1;
	private Possession toTake1;
	private Possession toPay2;
	private Possession toTake2;
	private Possession toPayChoosed;
	private Possession toTakeChoosed;

	
	
	public ConvertInput(Possession toPay1, Possession toTake1, Possession toPay2, Possession toTake2) {
		this.toPay1 = toPay1;
		this.toTake1 = toTake1;
		this.toPay2 = toPay2;
		this.toTake2 = toTake2;
	}



	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		ConvertController conversionMessage = new ConvertController(toPayChoosed, toTakeChoosed);
		remoteView.notifyController(conversionMessage);
	}

	public void chooseConversion(Scanner keyboard){
		String choice = keyboard.nextLine();
		switch (choice) {
		case "1":
			toPayChoosed = toPay1;
			toTakeChoosed = toTake1;
		case "2":
			toPayChoosed = toPay2;
			toTakeChoosed = toTake1;
		default: System.out.println("Invalid choice! Try again!");
			 chooseConversion(keyboard);
		}
	}
	
}
