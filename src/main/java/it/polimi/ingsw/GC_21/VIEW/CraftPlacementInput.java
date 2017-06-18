package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.BOARD.CraftType;


public class CraftPlacementInput extends PlacementInput {
	private CraftType craftType;

	@Override
	public void execute(RemoteView remoteView) {
		super.setAdapter(remoteView);
	    adapterConnection.out("Where do you want to place your Family Member? Be careful, my dear bischero: \n if you choose the " 
	        + "multiple action space you will get a malus on your craft! \n (1) Single Action Space - (2) Multiple Action Space"); 
	    String spaceType = adapterConnection.in();
	    super.execute(remoteView);
	    CraftPlacement craftPlacement = CraftPlacement.factoryCraftPlacement(remoteView.getPlayer(), familyMemberColor, remoteView.getGame().getBoard(), servantsToConvert, craftType, Integer.parseInt(spaceType)); 
	    remoteView.response(craftPlacement);
	}
	
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		selectCraftType(keyboard);
		super.inputFromCli(keyboard);

	}
	   
	  public void selectCraftType(Scanner keyboard){ 
	    System.out.println("Which kind of craft do you want to execute? (1) Production - (2) Harvest"); 
	    String choice = keyboard.nextLine(); 
	    switch (choice){ 
	      case "1":  craftType = CraftType.Production; 
	      case "2":  craftType = CraftType.Harvest; 
	      default: System.out.println("Invalid Input, ");
	    	   selectCraftType(keyboard); 
	    } 
	  } 
	  

}
