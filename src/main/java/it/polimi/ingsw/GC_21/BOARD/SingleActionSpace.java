package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class SingleActionSpace extends ActionSpace {

	private FamilyMember familyMemberLocated;

	public SingleActionSpace(int requiredDice, Immediate effect) {
		super(requiredDice, effect);
	}

	
	public boolean checkBusySpace() {
		if (this.familyMemberLocated != null){
			return true;
		}
		else {
			return false;
		}
	}

	public FamilyMember getFamilyMember() {
		return familyMemberLocated;
	}
	
	@Override
	public void place(FamilyMember familyMember) {
		setFamilyMember(familyMember);
	}
    

	public void setFamilyMember(FamilyMember familyMemberLocated) {
		this.familyMemberLocated = familyMemberLocated;
	}
	
	public void callIBonusEffect() {
		// TODO - implement SingleActionSpace.callIBonusEffect
		throw new UnsupportedOperationException();
	}

}