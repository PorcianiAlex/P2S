package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ChangeFamilyMemberValue extends Immediate {
	private final int newFamilyMemberValue;

	public ChangeFamilyMemberValue(Possession rewards, int newFamilyMemberValue, int privileges) {
		super(rewards, privileges);
		this.newFamilyMemberValue = newFamilyMemberValue;
	}

	public FamilyMember chooseFamilyMember(Player player){
		int i=0;
		return player.getFamilyMembers()[i];
	}
	
}