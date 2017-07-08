package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.REMOTEVIEW.CreatePlayerInput;
import it.polimi.ingsw.GC_21.REMOTEVIEW.InitGameInput;
import it.polimi.ingsw.GC_21.REMOTEVIEW.InputForm;

public class CheckLobbyMessage extends MessageToClient{

	private MessageToClient callMessage;


	public CheckLobbyMessage(boolean result, String description) {
		super(result, false, description);
		if (result) {
		}
	}
	
	
	public CheckLobbyMessage(CheckColorMessage checkColorMessage, boolean b, String string) {
		super(b, string);
		this.callMessage = checkColorMessage;
	}


	@Override
	public InputForm executeCLI(Object LOCK) throws InterruptedException {
		if (result) {
			inputForm = new CreatePlayerInput();
			if (callMessage != null) {
				callMessage.setInputForm(inputForm);
			}
			return super.executeCLI(LOCK);	
		}
		else {
			System.out.println(description);
			CheckLoginMessage checkLoginMessage = new CheckLoginMessage("Retry!", this);
			return checkLoginMessage.executeCLI(LOCK);
		}
	}

}
