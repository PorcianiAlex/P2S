package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftAction extends Action{
	CraftType craftType;

	public CraftAction(CraftType craftType, Player player) {
		super(player);
		this.craftType = craftType;
	}
	
}
