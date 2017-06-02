package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class EarningInfluencer extends Effect implements ToCallWhenEarning {
	private Possession earningInfluencer;

	
	public EarningInfluencer(Possession earningInfluencer) {
		this.earningInfluencer = earningInfluencer;
	}

	public void setNewReward(Possession reward){
		reward.subtract(earningInfluencer);
	}
	
	@Override
	public void activateEffect(Player player, Action action) {
		if (action instanceof PlacementAction){
			PlacementAction thisAction = (PlacementAction) action;
			if (thisAction.getSelectedActionSpace().getActionSpaceEffect()!=null){
				Possession rewardEffected = thisAction.getSelectedActionSpace().getActionSpaceEffect().getRewards();
				setNewReward(rewardEffected);
			}
		}
	}
	
	

}
