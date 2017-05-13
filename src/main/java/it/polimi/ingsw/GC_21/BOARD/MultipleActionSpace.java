package it.polimi.ingsw.GC_21.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class MultipleActionSpace extends ActionSpace {
	private ArrayList<FamilyMember> familyMember= new ArrayList<FamilyMember>();

	public MultipleActionSpace(int requiredDice,Immediate effect) {
		super(requiredDice, effect);
	}


	public ArrayList<FamilyMember> getFamilyMember() {
		return familyMember;
	}


	public void setFamilyMember(ArrayList<FamilyMember> familyMember) {
		this.familyMember = familyMember;
	}

	public void callMalusDiceEffect() {
		// TODO - implement MultipleActionSpace.callMalusDiceEffect
		throw new UnsupportedOperationException();
	}

}