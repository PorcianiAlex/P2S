package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.MarketPlacement;

public class MarketPlacementInput extends PlacementInput {
	private int AreaToPlace;
	
	public MarketPlacementInput(int areaToPlace) {
		AreaToPlace = areaToPlace;
	}
	
	public MarketPlacementInput() {	
	}

	public MarketPlacementInput(ActionInput actionInput, StringBuffer input) {
		super(actionInput, input);
	}

	@Override
	public void execute(RemoteView remoteView) {
		MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(remoteView.getPlayer(), familyMemberColor, AreaToPlace, servantsToConvert, remoteView.getGame().getBoard()); 
		remoteView.response(marketPlacement);					     		   
	}
	
	@Override
	public void inputFromCli() throws InterruptedException {
		System.out.println("Which reward do you want? \n [5x Coins (1) - 5x Servants (2) - 3x Military Points + 2x Coins (3) - 2x Privileges (4)"); 
		String Areastring = takeInput(actionInput); 
		try {
			AreaToPlace = Integer.parseInt(Areastring);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input, choose a Number!");
			this.inputFromCli();
		}
		super.inputFromCli();
	}

}
