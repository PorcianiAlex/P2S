package it.polimi.ingsw.GC_21.VIEW;


import it.polimi.ingsw.GC_21.ACTION.CouncilPlacement;

public class CouncilPlacementInput extends PlacementInput{
	
	public CouncilPlacementInput(ActionInput actionInput, StringBuffer input, boolean blackPlayer) {
		super(actionInput, input, blackPlayer);
	}

	public CouncilPlacementInput() {
	}

	@Override
	public void execute(RemoteView remoteView) {
		CouncilPlacement councilPlacement = CouncilPlacement.factoryCouncilPlacement(remoteView.getPlayer(), familyMemberColor, remoteView.getGame().getBoard(), servantsToConvert);   
	    remoteView.response(councilPlacement); 		
	}
	
	
}
