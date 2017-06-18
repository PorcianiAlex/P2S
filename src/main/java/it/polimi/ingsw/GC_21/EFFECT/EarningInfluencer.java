package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class EarningInfluencer extends Effect implements ToCallWhenEarning, Permanent {
	private Possession earningInfluencer;

	
	public EarningInfluencer(Possession earningInfluencer) {
		this.earningInfluencer = earningInfluencer;
	}

	public void setNewReward(Possession reward){
		reward.subtract(earningInfluencer);
	}
	
	
	/*Quite difficult to understand: this effect is the one given by the excomm cards.
	 * Each time you get some resources, they get a malus by this permanent effect, but you must check 
	 * if you get them by cards or by placement effect.
	*/
	@Override
	public void activateEffect(Player player, Action action) {
		if (action instanceof PlacementAction){
			PlacementAction thisAction = (PlacementAction) action;
			if (thisAction.getSelectedActionSpace().getActionSpaceEffect()!=null){
				Possession rewardEffected = thisAction.getSelectedActionSpace().getActionSpaceEffect().getRewards();
				setNewReward(rewardEffected);
			}
			if (action instanceof TowerPlacement){
				TowerPlacement thisAction2 = (TowerPlacement) action;
				if (thisAction2.getSelectedFloor().getDevCardPlace().getCard()!= null && thisAction2.getSelectedFloor().getDevCardPlace().getCard().getImmediateEffect()!=null){
					Possession rewardEffected2 = thisAction2.getSelectedFloor().getDevCardPlace().getCard().getImmediateEffect().getRewards();
					setNewReward(rewardEffected2);
				}
			}
		}
	}
	
}
	
	
