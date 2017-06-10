package it.polimi.ingsw.GC_21.BOARD;

import javax.management.monitor.GaugeMonitor;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ActionSpace {
	protected Game game;
	protected int requiredDice;
	protected Effect actionSpaceEffect;
	protected boolean Busy;

	public ActionSpace(int requiredDice, Effect actionSpaceEffect, Game game) {
		this.game = game;
		this.requiredDice = requiredDice;
		this.actionSpaceEffect = actionSpaceEffect;
		this.Busy = false;
	}
	
	
	public void setActionSpaceEffect(Effect actionSpaceEffect) {
		this.actionSpaceEffect = actionSpaceEffect;
	}



	@Override
	public String toString() {
		return  " requiredDice=" + requiredDice + ", Busy=" + Busy + "]";
	}


	public void callSpaceEffect(Player playerInAction, Action action){
		if (actionSpaceEffect!=null) {
			actionSpaceEffect.activateEffect(playerInAction, action);
		}
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