package it.polimi.ingsw.GC_21.CONTROLLER;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class SetFamilyMemberController extends ControllerForm{
	private int newFamilyMemberValue;
	private FamilyMemberColor selectedFamilyMember;
	private Player player;
	
	public SetFamilyMemberController(int newFamilyMemberValue, Player player, FamilyMemberColor selectedFamilyMember) {
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
