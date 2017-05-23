package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.PLAYER.Player;

public abstract class Action {
	protected final Player playerInAction;
	
	public Action(Player playerInAction) {
		this.playerInAction = playerInAction;
	}

	public void Execute() {
		
	}

	public Player getPlayerInAction() {
		return playerInAction;
	}


}