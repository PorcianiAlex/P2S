package it.polimi.ingsw.GC_21.BOARD;


import java.io.Serializable;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ActionSpace implements Serializable {
	protected Game game;
	protected int requiredDice;
	protected Effect actionSpaceEffect;
	protected boolean busy;
	protected boolean black;

	public ActionSpace(int requiredDice, Effect actionSpaceEffect, Game game) {
		this.game = game;
		this.requiredDice = requiredDice;
		this.actionSpaceEffect = actionSpaceEffect;
		this.busy = false;
	}
	
	
	public void setActionSpaceEffect(Effect actionSpaceEffect) {
		this.actionSpaceEffect = actionSpaceEffect;
	}



	@Override
	public String toString() {
		return  " requiredDice=" + requiredDice + ", Busy=" + busy + "]";
	}


	public void callSpaceEffect(Player playerInAction, Action action){
		if (actionSpaceEffect!=null) {
			actionSpaceEffect.activateEffect(playerInAction, action);
		}
	}
	
	public int getRequiredDice() {
		return requiredDice;
	}

	public void setRequiredDice(int requiredDice) {
		this.requiredDice = requiredDice;
	}
	
	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public Effect getActionSpaceEffect() {
		return actionSpaceEffect;
	}


	public void placeFamilyMember(FamilyMember familyMamber) {		
	}


	public boolean isBlack() {
		return black;
	}


	public void setBlack(boolean black) {
		this.black = black;
	}
	
	
}