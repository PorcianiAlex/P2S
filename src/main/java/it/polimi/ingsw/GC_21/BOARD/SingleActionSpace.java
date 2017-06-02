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
			setFamilyMember(familyMember);
			setBusy(true);
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
	
	
	public FamilyMember getFamilyMember() {
		return familyMemberLocated;
	}





	@Override
	public String toString() {
		if (familyMemberLocated!=null){
			return "[familyMember=" + familyMemberLocated.getPlayerColor().toString() + "//" + familyMemberLocated.getFamilyMemberColor().toString() + "]";
		}
		else {
			return "[familyMember=" + "//" + "]";
		}
	}
	
	
}