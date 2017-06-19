package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.MarketPlacement;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class MarketPlacementInput extends PlacementInput {
	private int AreaToPlace;
	
	public MarketPlacementInput(int areaToPlace) {
		super();
		AreaToPlace = areaToPlace;
	}
	
	public MarketPlacementInput() {
		
	}


	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(remoteView.getPlayer(), familyMemberColor, AreaToPlace, servantsToConvert, remoteView.getGame().getBoard()); 
		remoteView.response(marketPlacement);					     		   
	}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		System.out.println("Which reward do you want? \n [2x Coins (1) - 2x Servants (2) - 3x Military Points + 2x Coins (3) - 2x Privileges (4)"); 
		String Areastring = keyboard.nextLine(); 
		AreaToPlace = Integer.parseInt(Areastring);
		super.inputFromCli(keyboard);
	}

}
