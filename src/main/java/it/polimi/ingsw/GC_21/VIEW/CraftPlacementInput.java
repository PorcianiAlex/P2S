package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;


public class CraftPlacementInput extends PlacementInput {
	private CraftType craftType;
	private int spaceType;
	
	

	public CraftPlacementInput(CraftType craftType, int spaceType) {
		super();
		this.craftType = craftType;
		this.spaceType = spaceType;
	}
	
	public CraftPlacementInput() {
	}



	@Override
	public void execute(RemoteView remoteView) {
	    super.execute(remoteView);
	    CraftPlacement craftPlacement = CraftPlacement.factoryCraftPlacement(remoteView.getPlayer(), familyMemberColor, remoteView.getGame().getBoard(), servantsToConvert, craftType, spaceType); 
	    remoteView.response(craftPlacement);
	}
	
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		selectCraftType(keyboard);
		selectSpace(keyboard);
		super.inputFromCli(keyboard);

	}
	   
	  public void selectCraftType(Scanner keyboard){ 
	    System.out.println("Which kind of craft do you want to execute? \n(1) Production - (2) Harvest"); 
	    String choice = keyboard.next(); 
	    
	    switch (choice){ 
	      case "1":  craftType = CraftType.Production; 
	      				break;
	      case "2":  craftType = CraftType.Harvest; 
	      				break;
	      default: System.out.println("Invalid Input, ");
	      			selectCraftType(keyboard); 
	    } 
	  } 
	  
	  public void selectSpace(Scanner keyboard) {
		  System.out.println("Where do you want to place your Family Member? Be careful, my dear bischero: \n if you choose the " 
			        + "multiple action space you will get a malus on your craft! \n (1) Single Action Space - (2) Multiple Action Space"); 
		  String spacestring = keyboard.next();
		  spaceType = Integer.parseInt(spacestring);
	}
	  

}
