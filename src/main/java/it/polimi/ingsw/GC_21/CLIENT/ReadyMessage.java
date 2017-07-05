package it.polimi.ingsw.GC_21.CLIENT;

import it.polimi.ingsw.GC_21.VIEW.InputForm;

public class ReadyMessage extends MessageToClient{

	public ReadyMessage(String description) {
		super(true, true, description);
	}
	
	@Override
	public InputForm executeCLI(Object LOCK) throws InterruptedException {
		inputForm = new InputForm();
		return inputForm;
	}

}
