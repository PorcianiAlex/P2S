package it.polimi.ingsw.GC_21.REMOTEVIEW;


import it.polimi.ingsw.GC_21.ACTION.CouncilPlacement;

public class CouncilPlacementInput extends PlacementInput{
	
	public CouncilPlacementInput(ActionInput actionInput, StringBuffer input) {
		super(actionInput, input);
	}

	public CouncilPlacementInput() {
	}

	@Override
	public void execute(RemoteView remoteView) {
		CouncilPlacement councilPlacement = CouncilPlacement.factoryCouncilPlacement(remoteView.getPlayer(), familyMemberColor, remoteView.getGame().getBoard(), servantsToConvert);   
	    remoteView.response(councilPlacement); 		
	}
	
	
}
