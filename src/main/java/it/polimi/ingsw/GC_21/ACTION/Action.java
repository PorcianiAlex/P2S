package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.PLAYER.Player;

public abstract class Action {
	private Player playerInAction;

	
	public Action(Player playerInAction) {
		super();
		this.playerInAction = playerInAction;
	}

	public void Execute() {
		// TODO - implement Action.Execute
		throw new UnsupportedOperationException();
	}

	public Player getPlayerInAction() {
		return playerInAction;
	}

	public void setPlayerInAction(Player playerInAction) {
		this.playerInAction = playerInAction;
	}
	

}