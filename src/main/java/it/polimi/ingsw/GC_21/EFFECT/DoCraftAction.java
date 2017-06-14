package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.view.CraftInput;
import it.polimi.ingsw.GC_21.view.CraftPlacementInput;

public class DoCraftAction extends Effect {
	private CraftType craftType;
	protected int actionValueInfluencer;
	protected int actionValueBonus;
	
	public DoCraftAction(Game game, Possession rewards, CraftType craftType, int actionValueInfluencer, int actionValueBonus, int privileges) {
		super(rewards, privileges, game);
		this.craftType = craftType;
		this.actionValueInfluencer = actionValueInfluencer;
		this.actionValueBonus = actionValueBonus;
	}
	
	@Override
	// At first this effect give the player the rewards, then executes a craft
	public void activateEffect(Player player, Action action){
		super.activateEffect(player, action);
		PlacementAction placementAction = (PlacementAction) action;
		if (actionValueInfluencer==0){
			/*CraftAction craftAction = new CraftAction(player, craftType, placementAction.getActionValue()+actionValueBonus);
			game.notifyCurrentString("Thanks to an effect, you're going to execute a craft with value: " + craftAction.getActionValue());
			craftAction.Execute();*/
			CraftInput craftInput = new CraftInput(craftType, placementAction.getActionValue() + actionValueBonus);
			game.notifyCurrent(craftInput);
		}
		else{
			CraftInput craftInput = new CraftInput(craftType, actionValueInfluencer);
			game.notifyCurrent(craftInput);
		}
	}
}
