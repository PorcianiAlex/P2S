package it.polimi.ingsw.GC_21.BOARD;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ActionSpace {

	protected int requiredDice;
	protected final Effect actionSpaceEffect;
	protected boolean Busy;
	
	public ActionSpace(int requiredDice, Immediate actionSpaceEffect) {
		this.requiredDice = requiredDice;
		this.actionSpaceEffect = actionSpaceEffect;
		this.Busy = false;
	}
	

	public void callSpaceEffect(Player playerInAction){
		actionSpaceEffect.activateEffect(playerInAction);
	}
	
	public void placeFamilyMember(FamilyMember familyMamber) {	
	}

	public int getRequiredDice() {
		return requiredDice;
	}

	public void setRequiredDice(int requiredDice) {
		this.requiredDice = requiredDice;
	}
	
	public boolean isBusy() {
		return Busy;
	}

	public void setBusy(boolean busy) {
		Busy = busy;
	}

	public Effect getActionSpaceEffect() {
		return actionSpaceEffect;
	}
}