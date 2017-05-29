package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class SingleActionSpace extends ActionSpace {

	private FamilyMember familyMemberLocated;

	public SingleActionSpace(int requiredDice, Immediate effect) {
		super(requiredDice, effect);
	}

	
	

	
	@Override
	public void placeFamilyMember(FamilyMember familyMember) {
		if (familyMember != null){
			setFamilyMember(familyMember);
			familyMember.setPlaced(true);
			setBusy(true);
		}
		
	}
    

	public FamilyMember getFamilyMemberLocated() {
		return familyMemberLocated;
	}


	public void setFamilyMemberLocated(FamilyMember familyMemberLocated) {
		this.familyMemberLocated = familyMemberLocated;
	}


	public void setFamilyMember(FamilyMember familyMemberLocated) {
		this.familyMemberLocated = familyMemberLocated;
	}
	
	public void callIBonusEffect() {
		// TODO - implement SingleActionSpace.callIBonusEffect
		throw new UnsupportedOperationException();
	}

	public FamilyMember getFamilyMember() {
		return familyMemberLocated;
	}
}