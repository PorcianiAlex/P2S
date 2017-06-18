package it.polimi.ingsw.GC_21.view;

import java.awt.im.spi.InputMethod;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;

public class TakeCardInput extends InputForm{
	private DevCardType devCardType;
	private int actionValueInfluencer;
	private Possession discount;
	private int selectedFloor;
	
	

	public TakeCardInput(DevCardType devCardType, int actionValueInfluencer, Possession discount) {
		this.devCardType = devCardType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.discount = discount;
	}



	@Override
	public void execute(RemoteView remoteView) {
		super.setAdapter(remoteView);
		TowerPlacement takeCardAction = TowerPlacement.factoryTakeCard(remoteView.getPlayer(), devCardType, selectedFloor, actionValueInfluencer, discount, null, remoteView.getGame().getBoard());
		boolean result = remoteView.notifyObservers(takeCardAction);
		if (!result){
			this.execute(remoteView);
		}
		
		
	

	}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		adapterConnection.out("Hey sgangherato, you can take another Card!!!\nYour new action value is " + this.actionValueInfluencer);
		TowerPlacementInput towerPlacementInput = new TowerPlacementInput();
		devCardType = towerPlacementInput.selectTower(keyboard);
		adapterConnection.out("The kind of Card you can now take is " + this.devCardType.toString());
		selectedFloor = towerPlacementInput.selectFloor(keyboard);		
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
