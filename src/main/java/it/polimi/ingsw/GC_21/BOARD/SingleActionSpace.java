package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class SingleActionSpace extends ActionSpace {

	private boolean bonus;

	public void checkBusy() {
		// TODO - implement SingleActionSpace.checkBusy
		throw new UnsupportedOperationException();
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(FamilyMember familyMember) {
		this.familyMember = familyMember;
	}
	
	public void callIBonusEffect() {
		// TODO - implement SingleActionSpace.callIBonusEffect
		throw new UnsupportedOperationException();
	}

}