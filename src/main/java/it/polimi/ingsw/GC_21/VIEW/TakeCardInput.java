package it.polimi.ingsw.GC_21.VIEW;

import java.awt.im.spi.InputMethod;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;

public class TakeCardInput extends InputForm{
	private DevCardType devCardType;
	private int actionValueInfluencer;
	private Possession discount;
	
	

	public TakeCardInput(DevCardType devCardType, int actionValueInfluencer, Possession discount) {
		this.devCardType = devCardType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.discount = discount;
	}



	/*@Override
	public void execute(RemoteView remoteView) {
		super.setAdapter(remoteView);
		adapterConnection.out("Hey sgangherato, you can take another Card!!!\nYour new action value is " + this.actionValueInfluencer);
		TowerPlacementInput towerPlacementInput = new TowerPlacementInput();
		devCardType = towerPlacementInput.selectTower();
		adapterConnection.out("The kind of Card you can now take is " + this.devCardType.toString());
		int selectedFloor = towerPlacementInput.selectFloor();
		TowerPlacement takeCardAction = TowerPlacement.factoryTakeCard(remoteView.getPlayer(), devCardType, selectedFloor, actionValueInfluencer, discount, null, remoteView.getGame().getBoard());
		boolean result = remoteView.notifyObservers(takeCardAction);
		if (!result){
			this.execute(remoteView);
		}*/

		
			
			
		
		
	
					

	
	


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
