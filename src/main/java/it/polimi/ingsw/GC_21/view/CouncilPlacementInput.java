package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.ACTION.CouncilPlacement;

public class CouncilPlacementInput extends PlacementInput{
	
	@Override
	public void execute(RemoteView remoteView) {
		super.setAdapter(remoteView);
		super.execute(remoteView);
		CouncilPlacement councilPlacement = CouncilPlacement.factoryCouncilPlacement(remoteView.getPlayer(), familyMemberColor, remoteView.getGame().getBoard(), servantsToConvert);   
	    remoteView.response(councilPlacement); 		
	}
	
	

}
