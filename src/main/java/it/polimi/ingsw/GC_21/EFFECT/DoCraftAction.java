package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DoCraftAction extends Immediate {
	private CraftType craftType;
	private int actionValueInfluencer;

	
	public DoCraftAction(Possession rewards, CraftType craftType, int actionValueInfluencer) {
		super(rewards);
		this.craftType = craftType;
		this.actionValueInfluencer=actionValueInfluencer;
	}
	
	@Override
	// At first this effect give the player the rewards, then executes a craft
	public void activateEffect(Player player){
		super.activateEffect(player);
		CraftAction craftAction = new CraftAction(player, actionValueInfluencer, null, null, null, craftType, null);
	}
}
