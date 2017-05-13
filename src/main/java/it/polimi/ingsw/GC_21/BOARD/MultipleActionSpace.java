package it.polimi.ingsw.GC_21.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class MultipleActionSpace extends ActionSpace {
	
	private ArrayList<FamilyMember> familyMember= new ArrayList<FamilyMember>();
	private int callMalusOnDice;

	
	public ArrayList<FamilyMember> getFamilyMember() {
		return familyMember;
	}


	public void setFamilyMember(ArrayList<FamilyMember> familyMember) {
		this.familyMember = familyMember;
	}


	public int getCallMalusOnDice() {
		return callMalusOnDice;
	}


	public void setCallMalusOnDice(int callMalusOnDice) {
		this.callMalusOnDice = callMalusOnDice;
	}


	public void callMalusDiceEffect() {
		// TODO - implement MultipleActionSpace.callMalusDiceEffect
		throw new UnsupportedOperationException();
	}

}