package it.polimi.ingsw.GC_21.REMOTEVIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class PlacementInput extends ActionInput{
	protected ActionInput actionInput;
	protected FamilyMemberColor familyMemberColor; 
	protected int servantsToConvert;
	

	public PlacementInput(ActionInput actionInput, StringBuffer input) {
		this.actionInput = actionInput;
		this.input = input;
	}

	public PlacementInput() {
		// TODO Auto-generated constructor stub
	}

	public void execute(RemoteView remoteView) {
		int playerServant = remoteView.getPlayer().getMyPersonalBoard().getMyPossession().getServants().getValue(); 
	    if (playerServant == 0){ 
	    	ChooseActionMessage retryActionMessage = new ChooseActionMessage(false, "You don't have servant to convert!", remoteView.getPlayer());
	    	adapterConnection.sendObject(retryActionMessage);
	    	remoteView.inputObject();
	    } 
	    if (servantsToConvert > playerServant){ 
	    	ChooseActionMessage retryActionMessage = new ChooseActionMessage(false, "You don't have enough servant to convert, try again!", remoteView.getPlayer());
	    	adapterConnection.sendObject(retryActionMessage);
	    	remoteView.inputObject();
		    }
		   
	}
	
	@Override
	public void inputFromCli() throws InterruptedException {
		familyMemberColor = this.chooseFamilyMember(actionInput); 
	    servantsToConvert = this.chooseHowManyServants(actionInput); 
	}
	
	
	 public int chooseHowManyServants(InputForm craftInput) throws InterruptedException{ 
		    System.out.println("How many servants do you want to convert?:"); 
		    String servantString = takeInput(craftInput);
		    int servantsToConvert = Integer.parseInt(servantString); 
		    return servantsToConvert; 

	 } 
	 
	 public FamilyMemberColor chooseFamilyMember(InputForm setFamilyMemberInput) throws InterruptedException{ 
		    System.out.println("Select Family Member [ N - O - W - B ]:"); 
		    String choice = takeInput(setFamilyMemberInput);
		    switch (choice) { 
		    case "N": return FamilyMemberColor.Neutral; 
		    case "O": return FamilyMemberColor.Orange; 
		    case "W": return FamilyMemberColor.White; 
		    case "B": return FamilyMemberColor.Black; 
		    default: System.out.println("Invalid Family Member choice, try again!"); 
		      return this.chooseFamilyMember(setFamilyMemberInput); 
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
