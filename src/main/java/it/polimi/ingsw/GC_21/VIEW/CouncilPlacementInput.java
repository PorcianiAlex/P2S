package it.polimi.ingsw.GC_21.VIEW;

import it.polimi.ingsw.GC_21.ACTION.CouncilPlacement;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class CouncilPlacementInput extends PlacementInput{
	
	
	
	public CouncilPlacementInput( FamilyMemberColor familyMemberColor, int servantsToConvert) {
		super();
	}
	
	public CouncilPlacementInput() {
	}
	
	

	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		CouncilPlacement councilPlacement = CouncilPlacement.factoryCouncilPlacement(remoteView.getPlayer(), familyMemberColor, remoteView.getGame().getBoard(), servantsToConvert);   
	    remoteView.response(councilPlacement); 		
	}
	
	

}
