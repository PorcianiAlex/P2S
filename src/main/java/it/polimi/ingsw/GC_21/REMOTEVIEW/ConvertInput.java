package it.polimi.ingsw.GC_21.REMOTEVIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.CONTROLLER.ConvertController;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;

public class ConvertInput extends InputForm {
	private Possession toPay1;
	private Possession toTake1;
	private Possession toPay2;
	private Possession toTake2;
	private Possession toPayChosen;
	private Possession toTakeChosen;

	
	
	public ConvertInput( Possession toPay1, Possession toTake1,
			Possession toPay2, Possession toTake2, Possession toPayChosen, Possession toTakeChosen) {
		
		this.toPay1 = toPay1;
		this.toTake1 = toTake1;
		this.toPay2 = toPay2;
		this.toTake2 = toTake2;
		this.toPayChosen = toPayChosen;
		this.toTakeChosen = toTakeChosen;
	}



	public ConvertInput(Possession toPay1, Possession toTake1, Possession toPay2, Possession toTake2) {
		this.toPay1 = toPay1;
		this.toTake1 = toTake1;
		this.toPay2 = toPay2;
		this.toTake2 = toTake2;
	}



	@Override
	public void execute(RemoteView remoteView) {
		ConvertController conversionMessage = new ConvertController(toPayChosen, toTakeChosen);
		remoteView.notifyController(conversionMessage);
	}
	
	@Override
	public void inputFromCli() throws InterruptedException{
		String choice = takeInput(this);
		switch (choice) {
		case "1":
			toPayChosen = toPay1;
			toTakeChosen = toTake1;
			break;
		case "2":
			toPayChosen = toPay2;
			toTakeChosen = toTake1;
			break;
		default: System.out.println("Invalid choice! Try again!");
			 inputFromCli();
		}
	}
	
}
