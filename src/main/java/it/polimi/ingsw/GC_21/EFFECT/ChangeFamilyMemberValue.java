package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.FamilyMemberColor;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ChangeFamilyMemberValue extends Immediate {
	private final int newFamilyMemberValue;

	public ChangeFamilyMemberValue(Possession rewards, int newFamilyMemberValue) {
		super(rewards);
		this.newFamilyMemberValue = newFamilyMemberValue;
	}

	public FamilyMember chooseFamilyMember(Player player){
		int i=0;
		return player.getFamily().get(i);
	}
	
}