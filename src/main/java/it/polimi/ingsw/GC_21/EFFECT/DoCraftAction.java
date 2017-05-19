package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DoCraftAction extends Immediate {
	private CraftType craftType;

	public DoCraftAction(Possession rewards, CraftType craftType) {
		super(rewards);
		this.craftType = craftType;
	}
	
	@Override
	// At first this effect give the player the rewards, then executes a craft
	public void activateEffect(Player player){
		if (rewards!=null){
			super.activateEffect(player);
		}
		CraftAction craftAction = new CraftAction(craftType, player);
		craftAction.Execute();
	}
}
