package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class TowerPlacementInput extends PlacementInput {
	private DevCardType selectedTower; 
    private int floor;
     
	
	public TowerPlacementInput(DevCardType selectedTower, int floor) {
		this.selectedTower = selectedTower;
		this.floor = floor;
	}
	
	public TowerPlacementInput() {
	}

	@Override
	public void execute(RemoteView remoteView) {
		setAdapter(remoteView);
	    TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(remoteView.getPlayer(), familyMemberColor, selectedTower, floor, servantsToConvert, remoteView.getGame().getBoard()); 
	    remoteView.response(towerPlacement);
	}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
	    selectedTower = selectTower(keyboard); 
	    floor = selectFloor(keyboard);
		super.inputFromCli(keyboard); 
	}
 
	 public DevCardType selectTower(Scanner keyboard){ 
		    System.out.println("Select Tower [1-4]:"); 
		    String choice = keyboard.next(); 
		    keyboard.reset();
		    switch (choice) { 
		    case "1": return DevCardType.Territory; 
		    case "2": return DevCardType.Character; 
		    case "3": return DevCardType.Building; 
		    case "4": return DevCardType.Venture; 
		    default: System.out.println("Invalid floor choice, try again!"); 
		      return this.selectTower(keyboard);  
		    }   
	 } 
		   
	 
	 public int selectFloor(Scanner keyboard){ 
		    System.out.println("Select Floor [1-4]:"); 
		    String choicestring = keyboard.next();
		    keyboard.reset();
		    int choice = Integer.parseInt(choicestring); 
		    if (choice <=4 && choice >=1){ 
		      return choice; 
		    } 
		    else { 
		      System.out.println("Invalid floor choice, try again!"); 
		      return this.selectFloor(keyboard); 
		    } 
		  } 
	 

}
