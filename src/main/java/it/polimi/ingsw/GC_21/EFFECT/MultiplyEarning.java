package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class MultiplyEarning extends EarningInfluencer {
	private int multiplier;

	
	public MultiplyEarning(int multiplier) {
		super(new Possession());
		this.multiplier = multiplier;
	}

	@Override
	public void setNewReward(Possession reward) {
		reward.multiplyResource(multiplier);
	}

}
