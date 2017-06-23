package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.TakeCardInput;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class TakeCardMessage extends MessageToClient{
	private DevCardType devCardType;
	private int actionValueInfluencer;
	private Possession discount;
	private int selectedFloor;
	
	

	public TakeCardMessage(DevCardType devCardType, int actionValueInfluencer, Possession discount) {
		super(true, true, "Hey sgangherato, you can take another Card!!!\nYour new action value is " + actionValueInfluencer);
		this.devCardType = devCardType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.discount = discount;
	}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		TakeCardInput takeCardInput = new TakeCardInput(devCardType, actionValueInfluencer, discount);
		takeCardInput.inputFromCli(keyboard);
		return takeCardInput;
	}
	
	@Override
	public void executeGUI(FXMLGameController gameController) {
			gameController.takeNewCard(devCardType, actionValueInfluencer, discount);	
	}
}
