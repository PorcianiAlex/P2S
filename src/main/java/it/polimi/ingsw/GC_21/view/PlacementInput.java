package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class PlacementInput extends InputFromView{
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
		      adapter.out("You don't have servant to convert!"); 
		      return 0; 
		    } 
		    adapter.out("How many servants do you want to convert?:"); 
		    String servstring = adapter.in(); 
		    int servantsToConvert = Integer.parseInt(servstring); 
		    if (servantsToConvert > playerServant){ 
		      adapter.out("You don't have enough servant to convert, try again!"); 
		      return this.chooseHowManyServants(remoteView); 
		    }
		    else { 
		      adapter.out("You are going to convert " + servantsToConvert + " servants"); 
		      return servantsToConvert; 
		    } 
		  } 
	 public FamilyMemberColor chooseFamilyMember(){ 
		    adapter.out("Select Family Member [ N - O - W - B ]:"); 
		    String choice = adapter.in(); 
		    switch (choice) { 
		    case "N": return FamilyMemberColor.Neutral; 
		    case "O": return FamilyMemberColor.Orange; 
		    case "W": return FamilyMemberColor.White; 
		    case "B": return FamilyMemberColor.Black;
		    default: adapter.out("Invalid family member choice, try again!"); 
		      return this.chooseFamilyMember(); 
		    } 
		  } 
	 

}
