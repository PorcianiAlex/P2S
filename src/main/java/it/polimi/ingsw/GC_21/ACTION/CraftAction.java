package it.polimi.ingsw.GC_21.ACTION;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.CraftArea;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforeCraft;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforePlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftAction extends Action{
	private final CraftType craftType;
	private int actionValue;

	public CraftAction(Player playerInAction, CraftType craftType, int actionValue) {
		super(playerInAction);
		this.craftType = craftType;
		this.actionValue = actionValue;
	}

	
	@Override
	public void Execute() {
		callBeforeCraftEffects();
		playerInAction.getMyPersonalBoard().activateCraft(craftType, actionValue);
	}


	public void callBeforeCraftEffects() {
		if (!playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects().isEmpty()){
			int size = playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects().size();
			ArrayList<ToCallBeforeCraft> effects = playerInAction.getMyPersonalBoard().getToCallBeforeCraftEffects();
			for (int i = 0; i < size; i++) {
				effects.get(i).activateEffect(playerInAction, this);
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
