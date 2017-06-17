package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DontSpend3Coins extends Effect implements Permanent {
	
	@Override
	public void activateEffect(Player player, Action action) {
		super.activateEffect(player, action);
		player.setOverchargeOnBusyTower(true);
	}

}
