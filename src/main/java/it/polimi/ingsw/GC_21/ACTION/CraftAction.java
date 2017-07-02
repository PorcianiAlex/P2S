package it.polimi.ingsw.GC_21.ACTION;

import java.util.ArrayList;


import it.polimi.ingsw.GC_21.BOARD.CraftType;

import it.polimi.ingsw.GC_21.EFFECT.*;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftAction extends Action{
	private final CraftType craftType;
	private int actionValue;
	private int servantsToConvert;

	public CraftAction(Player playerInAction, CraftType craftType, int servantsToConvert, int actionValue) {
		super(playerInAction);
		this.craftType = craftType;
		this.actionValue = actionValue;
		this.servantsToConvert = servantsToConvert;
	}
	
	public CraftAction(Player playerInAction, CraftType craftType, int actionValue) {
		super(playerInAction);
		this.craftType = craftType;
		this.actionValue = actionValue;
	}
	
	@Override
	public void Execute() {
		callBeforeCraftEffects();
		if (playerInAction.getMyPersonalBoard().getMyPossession().getServants().getValue()>=servantsToConvert){
			playerInAction.getMyPersonalBoard().getMyPossession().subtractItemToPossession(new Servants(servantsToConvert));
			playerInAction.getMyPersonalBoard().activateCraft(craftType, actionValue+servantsToConvert);
		}
		else{
			playerInAction.getMyPersonalBoard().activateCraft(craftType, actionValue);
		}
	}


	public void callBeforeCraftEffects() {
		if (!playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects().isEmpty()){
			int size = playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects().size();
			ArrayList<ToCallBeforeCraft> effects = playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects();
			for (int i = 0; i < size; i++) {
				((Effect) effects.get(i)).activateEffect(playerInAction, this);
			}
		}
	}
	
	public int getActionValue() {
		return actionValue;
	}


	public void setActionValue(int actionValue) {
		this.actionValue = actionValue;
	}


	public CraftType getCraftType() {
		return craftType;
	}
	
}
