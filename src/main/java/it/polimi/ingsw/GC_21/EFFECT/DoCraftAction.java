package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DoCraftAction extends Immediate {
	private CraftType craftType;
	protected int actionValueInfluencer;
	protected int actionValueBonus;
	
	public DoCraftAction(Possession rewards, CraftType craftType, int actionValueInfluencer, int actionValueBonus, int privileges) {
		super(rewards, privileges);
		this.craftType = craftType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.actionValueBonus = actionValueBonus;
	}
	
	@Override
	// At first this effect give the player the rewards, then executes a craft
	public void activateEffect(Player player, Action placementAction){
		super.activateEffect(player, placementAction);
		PlacementAction placementAction2 = (PlacementAction) placementAction;
		if (actionValueInfluencer==0){
			CraftAction craftAction = new CraftAction(player, craftType, placementAction2.getActionValue()+actionValueBonus);
			craftAction.Execute();
		}
		else{
			CraftAction craftAction = new CraftAction(player, craftType, actionValueInfluencer+actionValueBonus);
			craftAction.Execute();
		}
	}
}
