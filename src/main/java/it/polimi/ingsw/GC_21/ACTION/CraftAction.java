package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.CraftArea;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftAction extends Action{
	private CraftType craftType;
	private int actionValue;

	public CraftAction(Player playerInAction, CraftType craftType, int actionValue) {
		super(playerInAction);
		this.craftType = craftType;
		this.actionValue = actionValue;
	}

	public void executeCraft() {
		playerInAction.getMyPersonalBoard().checkCraftEffect(craftType, actionValue);
	}
	
	
}
