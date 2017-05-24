package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.ACTION.PlacementActionWithNoFamilyMember;
import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DoPlacementAction extends Immediate {
	private final int actionValueInfluencer;
	private final int actionValueBonus;
	private final DevCardType devCardType;
	private final Possession discount;	
	
	public DoPlacementAction(Possession rewards, int actionValueInfluencer, int actionValueBonus, DevCardType devCardType, Possession discount) {
		super(rewards);
		this.actionValueInfluencer = actionValueInfluencer;
		this.actionValueBonus = actionValueBonus;
		this.devCardType = devCardType;
		this.discount = discount;
	}

	public int getActionValueInfluencer() {
		return actionValueInfluencer;
	}
	
	public DevCardType getDevCardType() {
		return devCardType;
	}
	
	@Override
	public void activateEffect(Player player, Action placementAction) {
		super.activateEffect(player, placementAction);
		PlacementAction placementAction2 = (PlacementAction) placementAction;
		if (actionValueInfluencer==0){
			PlacementActionWithNoFamilyMember placementActionWithNoFamilyMember = new PlacementActionWithNoFamilyMember(player, placementAction2.getActionValue() + actionValueBonus, null, null, null, null, devCardType, discount);
		}
		else {
			PlacementActionWithNoFamilyMember placementActionWithNoFamilyMember = new PlacementActionWithNoFamilyMember(player, actionValueInfluencer + actionValueBonus, null, null, null, null, devCardType, discount);

		}
	}
	

}
