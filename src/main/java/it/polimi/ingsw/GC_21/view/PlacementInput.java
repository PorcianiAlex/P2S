package it.polimi.ingsw.GC_21.view;

import java.awt.im.spi.InputMethod;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;

public class PlacementInput extends InputFromView{
	private DevCardType devCardType;
	private int actionValueInfluencer;
	private Possession discount;
	
	

	public PlacementInput(DevCardType devCardType, int actionValueInfluencer, Possession discount) {
		this.devCardType = devCardType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.discount = discount;
	}



	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		adapter.out("Hey sgangherato, you can take another Card!!!\nYour new action value is " + this.actionValueInfluencer);
		devCardTypeChoice();
		adapter.out("The kind of Card you can now take is " + this.devCardType.toString());
		int selectedFloor = super.selectFloor();
		TowerPlacement takeCardAction = TowerPlacement.factoryTakeCard(remoteView.getPlayer(), devCardType, selectedFloor, actionValueInfluencer, discount, null, remoteView.getGame().getBoard());
		boolean result = remoteView.notifyObservers(takeCardAction);
		if (!result){
			this.execute(remoteView);
		}
	}
		
			
			
		
		
	
	public void devCardTypeChoice() {
		if (devCardType == null) {
			adapter.out("Which kind of Card do you want to take? \n(1) - Territory \n(2) - Building \n(3) - Venture \n(4) - Charater");
			String choice = adapter.in();
			switch (choice) {
			case "1": setDevCardType(DevCardType.Territory);
				break;
			case "2": setDevCardType(DevCardType.Building);
				break;
			case "3": setDevCardType(DevCardType.Venture);
				break;
			case "4": setDevCardType(DevCardType.Character);
				break;
			default: adapter.out("Invalid Input");
				devCardTypeChoice();
			}
		}
					

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
