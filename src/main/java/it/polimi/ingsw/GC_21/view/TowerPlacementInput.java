package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class TowerPlacementInput extends PlacementInput {
	
	
	
	@Override
	public void execute(RemoteView remoteView) {
		super.setAdapter(remoteView);
		super.execute(remoteView);
		DevCardType selectedTower; 
	    int floor; 
	    selectedTower = selectTower(); 
	    floor = selectFloor();   	    
	    TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(remoteView.getPlayer(), familyMemberColor, selectedTower, floor, servants, remoteView.getGame().getBoard()); 
	    remoteView.response(towerPlacement);
	}
 
	 public DevCardType selectTower(){ 
		    adapter.out("Select Tower [1-4]:"); 
		    String choice = adapter.in(); 
		    switch (choice) { 
		    case "1": return DevCardType.Territory; 
		    case "2": return DevCardType.Building; 
		    case "3": return DevCardType.Character; 
		    case "4": return DevCardType.Venture; 
		    default: adapter.out("Invalid floor choice, try again!"); 
		      return this.selectTower();  
		    }   
	 } 
		   
	 
	 public int selectFloor(){ 
		    adapter.out("Select Floor [1-4]:"); 
		    String choicestring = adapter.in(); 
		    int choice = Integer.parseInt(choicestring); 
		    if (choice <=4 && choice >=1){ 
		      return choice; 
		    } 
		    else { 
		      adapter.out("Invalid floor choice, try again!"); 
		      return this.selectFloor(); 
		    } 
		  } 
	 

}
