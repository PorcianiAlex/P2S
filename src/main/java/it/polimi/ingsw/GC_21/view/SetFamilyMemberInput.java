package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.SetFamilyMemberMessage;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class SetFamilyMemberInput extends InputFromView{
	private int newFamilyMemberValue;
	private Player player;
	

	
	public SetFamilyMemberInput(int newFamilyMemberValue, Player player) {
		super();
		this.newFamilyMemberValue = newFamilyMemberValue;
		this.player = player;
	}

	public int getNewFamilyMemberValue() {
		return newFamilyMemberValue;
	}

	public void setNewFamilyMemberValue(int newFamilyMemberValue) {
		this.newFamilyMemberValue = newFamilyMemberValue;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		adapter.out("One of your family member can be upgraded to value "
				+ newFamilyMemberValue);
		FamilyMemberColor selectedFamilyMember = this.chooseFamilyMember();
		SetFamilyMemberMessage setFamilyMemberMessage = new SetFamilyMemberMessage(newFamilyMemberValue, player, selectedFamilyMember);
		remoteView.notifyMessage(setFamilyMemberMessage);
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
