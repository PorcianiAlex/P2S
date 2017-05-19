package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ActionSpace {

	private int requiredDice;
	private final Effect actionSpaceEffect;
	
	public ActionSpace(int requiredDice, Immediate actionSpaceEffect) {
		this.requiredDice = requiredDice;
		this.actionSpaceEffect = actionSpaceEffect;
	}
	
	public void callSpaceEffect(){
		actionSpaceEffect.activateEffect();
	}
	
	public void place(FamilyMember familyMamber) {
		
	}

	public void checkDice() {
		// TODO - implement ActionSpace.checkDice
		throw new UnsupportedOperationException();
	}

	public int getRequiredDice() {
		return requiredDice;
	}

	public void setRequiredDice(int requiredDice) {
		this.requiredDice = requiredDice;
	}
		
}