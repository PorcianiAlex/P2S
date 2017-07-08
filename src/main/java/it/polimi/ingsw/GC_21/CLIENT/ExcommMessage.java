package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.REMOTEVIEW.ExcommInput;
import it.polimi.ingsw.GC_21.REMOTEVIEW.InputForm;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class ExcommMessage extends MessageToClient {

	public ExcommMessage() {
		super(true, true, "Bergoglio wants to know if you have been a great guy recently! \n"
				+ "Be careful: if you disappoint him, you will get a permanent malus!!!");
	}
	@Override
	public InputForm executeCLI(Object lOCK) throws InterruptedException {
		inputForm = new ExcommInput();
		return super.executeCLI(lOCK);
	}
	
	
	
	public void executeGUI(FXMLGameController gameController) {		
		gameController.excomm(description);
	}

}
