package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.PLAYER.Player;

public abstract class Action {
	protected final Player playerInAction;
	
	public Action(Player playerInAction) {
		this.playerInAction = playerInAction;
	}

	public boolean Execute() {
		// TODO - implement Action.Execute
		throw new UnsupportedOperationException();
	}

	public Player getPlayerInAction() {
		return playerInAction;
	}


}