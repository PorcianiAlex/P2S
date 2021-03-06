package it.polimi.ingsw.GC_21.BOARD;

import java.io.Serializable;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class SingleActionSpace extends ActionSpace implements Serializable{

	private FamilyMember familyMemberLocated;

	public SingleActionSpace(int requiredDice, Effect effect, Game game) {
		super(requiredDice, effect, game);
	}

	
	@Override
	public void placeFamilyMember(FamilyMember familyMember) {
		if (familyMember.getOwnerPlayer().getPlayerColor() == Color.Black) {
			setBlack(true);
		}
		else {
			setFamilyMemberLocated(familyMember);
			setBusy(true);
		}		
	}
    

	public FamilyMember getFamilyMemberLocated() {
		return familyMemberLocated;
	}


	public void setFamilyMemberLocated(FamilyMember familyMemberLocated) {
		this.familyMemberLocated = familyMemberLocated;
	}


	@Override
	public String toString() {
		if (familyMemberLocated!=null){
			return "[familyMember=" + familyMemberLocated.getOwnerPlayer().toString() + "//" + familyMemberLocated.getFamilyMemberColor().toString() + "]";
		}
		else {
			return "[familyMember=" + "//" + "]";
		}
	}
	
	
}