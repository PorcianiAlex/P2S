package it.polimi.ingsw.GC_21.VIEW;

import it.polimi.ingsw.GC_21.ACTION.MarketPlacement;

public class MarketPlacementInput extends PlacementInput {
	
	@Override
	public void execute(RemoteView remoteView) {
		super.setAdapter(remoteView);
		adapterConnection.out("Which reward do you want? \n [2x Coins (1) - 2x Servants (2) - 3x Military Points + 2x Coins (3) - 2x Privileges (4)"); 
		String Areastring = adapterConnection.in(); 
		int AreaToPlace = Integer.parseInt(Areastring); 
		super.execute(remoteView);
		MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(remoteView.getPlayer(), familyMemberColor, AreaToPlace, servantsToConvert, remoteView.getGame().getBoard()); 
		remoteView.response(marketPlacement);	     
			   
			 
			   
	}

}
