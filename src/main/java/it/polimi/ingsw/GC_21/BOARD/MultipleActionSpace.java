package it.polimi.ingsw.GC_21.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class MultipleActionSpace extends ActionSpace {
	private ArrayList<FamilyMember> familyMembersLocated= new ArrayList<FamilyMember>();

	public MultipleActionSpace(int requiredDice,Immediate effect) {
		super(requiredDice, effect);
	}


	public ArrayList<FamilyMember> getFamilyMember() {
		return familyMembersLocated;
	}
	
	@Override
	public void place(FamilyMember familyMamber) {
		familyMembersLocated.add(familyMamber);
	}


	public void setFamilyMember(ArrayList<FamilyMember> familyMember) {
		this.familyMembersLocated = familyMember;
	}

	public void callMalusDiceEffect() {
		// TODO - implement MultipleActionSpace.callMalusDiceEffect
		throw new UnsupportedOperationException();
	}

}