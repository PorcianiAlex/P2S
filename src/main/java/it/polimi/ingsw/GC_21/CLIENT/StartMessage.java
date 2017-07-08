package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.REMOTEVIEW.InputForm;
import it.polimi.ingsw.GC_21.REMOTEVIEW.LoginInput;

public class StartMessage extends MessageToClient{
	MessageToClient callMessage;
	
	public StartMessage() {
		super(true, false, "Start Game");
	}

	public StartMessage(CheckLoginMessage checkLoginMessage) {
		super(true, false, "Start Game");
		this.callMessage = checkLoginMessage;
	}

	@Override
	public InputForm executeCLI(Object lOCK) throws InterruptedException {
		inputForm = new LoginInput();
		if (callMessage != null) {
			callMessage.setInputForm(inputForm);
		}
		return super.executeCLI(lOCK);
	}
	
}
