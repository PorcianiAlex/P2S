package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.CraftType;

public class CraftInput extends InputForm {
	private CraftType craftType;
	private int actionValue;
	private int servantsToConvert;
	
	
	
	public CraftInput(CraftType craftType, int actionValue,
			int servantsToConvert) {
		this.craftType = craftType;
		this.actionValue = actionValue;
		this.servantsToConvert = servantsToConvert;
	}

	public CraftInput(CraftType craftType, int actionValue) {
		this.craftType = craftType;
		this.actionValue = actionValue;
	}
	
	@Override
	public void execute(RemoteView remoteView) {
		setAdapter(remoteView);
		CraftAction craftAction = new CraftAction(remoteView.getPlayer(), craftType, servantsToConvert, actionValue);
		remoteView.response(craftAction);
		}
	
	@Override
	public void inputFromCli() throws InterruptedException {
		PlacementInput placementInput = new PlacementInput();
		placementInput.chooseHowManyServants(this);
		servantsToConvert = placementInput.getServantsToConvert();
	}

}
