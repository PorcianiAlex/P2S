package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;

public class TakeCardInput extends ActionInput{
	private DevCardType devCardType;
	private int actionValueInfluencer;
	private Possession discount;
	private int selectedFloor;
	
	

	public TakeCardInput(DevCardType devCardType, int actionValueInfluencer, Possession discount) {
		this.devCardType = devCardType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.discount = discount;
	}
	
	public TakeCardInput(DevCardType devCardType, int actionValueInfluencer, Possession discount, int selectedfloor) {
		this.devCardType = devCardType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.discount = discount;
		this.selectedFloor=selectedfloor;
	}



	@Override
	public void execute(RemoteView remoteView) {
		super.setAdapter(remoteView);
		TowerPlacement takeCardAction = TowerPlacement.factoryTakeCard(remoteView.getPlayer(), devCardType, selectedFloor, actionValueInfluencer, discount, null, remoteView.getGame().getBoard());
		//remoteView.response(takeCardAction);
		remoteView.notifyObservers(takeCardAction);
	}
	
	@Override
	public void inputFromCli() throws InterruptedException {
		TowerPlacementInput towerPlacementInput = new TowerPlacementInput(this, input, false);
		if (devCardType == null) {
			devCardType = towerPlacementInput.selectTower();
		}
		System.out.println("The kind of Card you can now take is " + this.devCardType.toString());
		selectedFloor = towerPlacementInput.selectFloor();		
	}
			


	public DevCardType getDevCardType() {
		return devCardType;
	}



	public void setDevCardType(DevCardType devCardType) {
		this.devCardType = devCardType;
	}



	public int getActionValueInfluencer() {
		return actionValueInfluencer;
	}



	public void setActionValueInfluencer(int actionValueInfluencer) {
		this.actionValueInfluencer = actionValueInfluencer;
	}



	public Possession getDiscount() {
		return discount;
	}



	public void setDiscount(Possession discount) {
		this.discount = discount;
	}
	
	
	

}
