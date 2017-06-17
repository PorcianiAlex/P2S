package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class PlacementInput extends InputForm{
	protected FamilyMemberColor familyMemberColor; 
	protected int servants;
	
	@Override
	public void execute(RemoteView remoteView) {
		familyMemberColor = this.chooseFamilyMember(); 
	    servants = this.chooseHowManyServants(remoteView); 
	}
	
	 public int chooseHowManyServants(RemoteView remoteView){ 
		    int playerServant = remoteView.getPlayer().getMyPersonalBoard().getMyPossession().getServants().getValue(); 
		    if (playerServant == 0){ 
		      adapterConnection.out("You don't have servant to convert!"); 
		      return 0; 
		    } 
		    adapterConnection.out("How many servants do you want to convert?:"); 
		    String servstring = adapterConnection.in(); 
		    int servantsToConvert = Integer.parseInt(servstring); 
		    if (servantsToConvert > playerServant){ 
		      adapterConnection.out("You don't have enough servant to convert, try again!"); 
		      return this.chooseHowManyServants(remoteView); 
		    }
		    else { 
		      adapterConnection.out("You are going to convert " + servantsToConvert + " servants"); 
		      return servantsToConvert; 
		    } 
		  } 
	 public FamilyMemberColor chooseFamilyMember(){ 
		    adapterConnection.out("Select Family Member [ N - O - W - B ]:"); 
		    String choice = adapterConnection.in(); 
		    switch (choice) { 
		    case "N": return FamilyMemberColor.Neutral; 
		    case "O": return FamilyMemberColor.Orange; 
		    case "W": return FamilyMemberColor.White; 
		    case "B": return FamilyMemberColor.Black; 
		    default: adapterConnection.out("Invalid floor choice, try again!"); 
		      return this.chooseFamilyMember(); 
		    } 
		  } 
	 

}
