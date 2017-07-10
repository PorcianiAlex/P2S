package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.PLAYER.Player;


public class TowerPlacementInput extends PlacementInput {
	private DevCardType selectedTower; 
    private int floor;
    
     
	
	public TowerPlacementInput(DevCardType selectedTower, int floor) {
		super();
		this.selectedTower = selectedTower;
		this.floor = floor;
	}
	
	public TowerPlacementInput(ActionInput actionInput, StringBuffer input, boolean blackPlayer) {
		super(actionInput, input, blackPlayer);
		
	}

	@Override
	public void execute(RemoteView remoteView) {
		setAdapter(remoteView);
	    TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(remoteView.getPlayer(), familyMemberColor, selectedTower, floor, servantsToConvert, remoteView.getGame().getBoard()); 
	    remoteView.response(towerPlacement);
	}
	
	@Override
	public void inputFromCli() throws InterruptedException {
	    selectedTower = selectTower(); 
	    floor = selectFloor();
		super.inputFromCli(); 
	}
 
	 public DevCardType selectTower() throws InterruptedException{ 
		    System.out.println("Select Tower [1-4]:"); 
		    String choice = takeInput(actionInput);
		    switch (choice) { 
		    case "1": return DevCardType.Territory; 
		    case "2": return DevCardType.Character; 
		    case "3": return DevCardType.Building; 
		    case "4": return DevCardType.Venture; 
		    default: System.out.println("Invalid tower choice, try again!"); 
		      return this.selectTower();  
		    }   
	 } 
		   
	 
	 public int selectFloor() throws InterruptedException{ 
		    System.out.println("Select Floor [1-4]:");
		    String choiceString = takeInput(actionInput);
		    int choice;
			try {
				choice = Integer.parseInt(choiceString);
			} catch (NumberFormatException e) {
				System.out.println("Invalid floor choice, try again!"); 
			     return this.selectFloor(); 
			} 
		    if (choice <=4 && choice >=1){ 
		      return choice; 
		    } 
		    else { 
		      System.out.println("Invalid floor choice, try again!"); 
		      return this.selectFloor(); 
		    } 
		  } 
	 

}
