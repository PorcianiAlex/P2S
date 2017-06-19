package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class PlacementInput extends ActionInput{
	
	protected FamilyMemberColor familyMemberColor; 
	protected int servantsToConvert;
	
	
	public PlacementInput(String choice, FamilyMemberColor familyMemberColor, int servantsToConvert) {
		super(choice);
		this.familyMemberColor = familyMemberColor;
		this.servantsToConvert = servantsToConvert;
	}
	
	public PlacementInput() {		
	}

	public void execute(RemoteView remoteView) {
		int playerServant = remoteView.getPlayer().getMyPersonalBoard().getMyPossession().getServants().getValue(); 
	    if (playerServant == 0){ 
	      System.out.println("You don't have servant to convert!"); 
	    } 
	    if (servantsToConvert > playerServant){ 
		      System.out.println("You don't have enough servant to convert, try again!"); 
		    }
		    else { 
		      System.out.println("You are going to convert " + servantsToConvert + " servants"); 
		    } 	
		
	}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		familyMemberColor = this.chooseFamilyMember(keyboard); 
	    servantsToConvert = this.chooseHowManyServants(keyboard); 
	}
	
	
	 public int chooseHowManyServants(Scanner keyboard){ 
		    System.out.println("How many servants do you want to convert?:"); 
		    String servstring = keyboard.nextLine(); 
		    int servantsToConvert = Integer.parseInt(servstring); 
		    return servantsToConvert; 

	 } 
	 
	 public FamilyMemberColor chooseFamilyMember(Scanner keyboard){ 
		    System.out.println("Select Family Member [ N - O - W - B ]:"); 
		    String choice = keyboard.nextLine(); 
		    switch (choice) { 
		    case "N": return FamilyMemberColor.Neutral; 
		    case "O": return FamilyMemberColor.Orange; 
		    case "W": return FamilyMemberColor.White; 
		    case "B": return FamilyMemberColor.Black; 
		    default: System.out.println("Invalid Family Member choice, try again!"); 
		      return this.chooseFamilyMember(keyboard); 
		    } 
		  }

	public FamilyMemberColor getFamilyMemberColor() {
		return familyMemberColor;
	}

	public void setFamilyMemberColor(FamilyMemberColor familyMemberColor) {
		this.familyMemberColor = familyMemberColor;
	}

	public int getServantsToConvert() {
		return servantsToConvert;
	}

	public void setServantsToConvert(int servantsToConvert) {
		this.servantsToConvert = servantsToConvert;
	} 
	 

}
