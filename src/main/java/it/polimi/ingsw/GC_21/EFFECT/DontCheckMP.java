package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DontCheckMP extends Effect{
	
	@Override
	public void activateEffect(Player player, Action action) {
		player.setCheckOnMP(true);
	}

}
