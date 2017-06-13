package it.polimi.ingsw.GC_21.view;

import java.io.CharConversionException;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.ConvertMessage;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;

public class ConvertInput extends InputFromView {
	private Possession toPay1;
	private Possession toTake1;
	private Possession toPay2;
	private Possession toTake2;
	
	
	public ConvertInput(Possession toPay1, Possession toTake1, Possession toPay2, Possession toTake2) {
		super();
		this.toPay1 = toPay1;
		this.toTake1 = toTake1;
		this.toPay2 = toPay2;
		this.toTake2 = toTake2;
	}



	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		adapterConnection.out("You can choose between two conversion!\n(1) or (2)?");
		adapterConnection.out("If you pay: " + toPay1.toString() + ", you'll get: " + toTake1.toString());
		adapterConnection.out("If you pay: " + toPay2.toString() + ", you'll get: " + toTake2.toString());
		boolean choice = this.chooseConversion();
		if (choice==true){
			ConvertMessage conversionMessage = new ConvertMessage(toPay1, toTake1);
			remoteView.notifyMessage(conversionMessage);
		}
		else{
			ConvertMessage conversionMessage = new ConvertMessage(toPay1, toTake1);
			remoteView.notifyMessage(conversionMessage);
		}
	}

	public boolean chooseConversion(){
		String choice = adapterConnection.in();
		switch (choice) {
		case "1":
			return true;
		case "2":
			return false;
		default: adapterConnection.out("Invalid choice! Try again!");
			return chooseConversion();
		}
	}
	
}
