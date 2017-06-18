package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import it.polimi.ingsw.GC_21.CONTROLLER.ControllerForm;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class SetFamilyMemberMessage extends ControllerForm{
	private int newFamilyMemberValue;
	private FamilyMemberColor selectedFamilyMember;
	private Player player;
	
	public SetFamilyMemberMessage(int newFamilyMemberValue, Player player, FamilyMemberColor selectedFamilyMember) {
		super();
		this.newFamilyMemberValue = newFamilyMemberValue;
		this.player = player;
		this.selectedFamilyMember = selectedFamilyMember;
	}
	
	@Override
	public boolean executeController() {
		player.getSpecificFamilyMember(selectedFamilyMember).setActionValue(newFamilyMemberValue);
		return true;
	}
	
}
