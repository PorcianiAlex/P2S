package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.BOARD.CraftType;


public class CraftPlacementInput extends PlacementInput {
	private CraftType craftType;
	private int spaceType;
	
	

	public CraftPlacementInput(CraftType craftType, int spaceType) {
		this.craftType = craftType;
		this.spaceType = spaceType;
	}
	


	public CraftPlacementInput(ActionInput actionInput, StringBuffer input, boolean blackPlayer) {
		super(actionInput, input, blackPlayer);
	}

	@Override
	public void execute(RemoteView remoteView) {
	    CraftPlacement craftPlacement = CraftPlacement.factoryCraftPlacement(remoteView.getPlayer(), familyMemberColor, remoteView.getGame().getBoard(), servantsToConvert, craftType, spaceType); 
	    remoteView.response(craftPlacement);
	}
	
	
	@Override
	public void inputFromCli() throws InterruptedException {
		selectCraftType();
		selectSpace();
		super.inputFromCli();

	}
	   
	  public void selectCraftType() throws InterruptedException { 
	    System.out.println("Which kind of craft do you want to execute? \n(1) Production - (2) Harvest"); 
	    String choice = takeInput(actionInput);
	    switch (choice){ 
	      case "1":  craftType = CraftType.Production; 
	      				break;
	      case "2":  craftType = CraftType.Harvest; 
	      				break;
	      default: System.out.println("Invalid Input, ");
	      			selectCraftType(); 
	    } 
	  } 
	  
	  public void selectSpace() throws InterruptedException  {
		  System.out.println("Where do you want to place your Family Member? Be careful, my dear bischero: \n if you choose the " 
			        + "multiple action space you will get a malus on your craft! \n (1) Single Action Space - (2) Multiple Action Space"); 
		  String spacestring = takeInput(actionInput);
		  spaceType = Integer.parseInt(spacestring);
	}
	  

}
