package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.BOARD.CraftType;


public class CraftPlacementInput extends PlacementInput {

	@Override
	public void execute(RemoteView remoteView) {
		super.setAdapter(remoteView);
		CraftType craftType = selectCraftType(); 
	    adapterConnection.out("Where do you want to place your Family Member? Be careful, my dear bischero: \n if you choose the " 
	        + "multiple action space you will get a malus on your craft! \n (1) Single Action Space - (2) Multiple Action Space"); 
	    String spaceType = adapterConnection.in();
	    super.execute(remoteView);
	    CraftPlacement craftPlacement = CraftPlacement.factoryCraftPlacement(remoteView.getPlayer(), familyMemberColor, remoteView.getGame().getBoard(), servants, craftType, Integer.parseInt(spaceType)); 
	    remoteView.response(craftPlacement);
	}
	
	   
	  public CraftType selectCraftType(){ 
	    adapterConnection.out("Which kind of craft do you want to execute? (1) Production - (2) Harvest"); 
	    String craftType = adapterConnection.in(); 
	    switch (craftType){ 
	      case "1": return CraftType.Production; 
	      case "2": return CraftType.Harvest; 
	      default: return CraftType.Production; 
	    } 
	  } 
	  

}
