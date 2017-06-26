package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.CLIENT.PrivilegeMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DiscardLeaderCard extends Action{
	private int leaderChosen;
	
	public DiscardLeaderCard(Player playerInAction, int leaderChosen, Game game) {
		super(playerInAction);
		this.leaderChosen = leaderChosen;
		this.game = game;
	}



	@Override
	public void Execute() {
		playerInAction.getMyPersonalBoard().getLeaderCards().remove(leaderChosen);
		PrivilegeMessage privilegeMessage = new PrivilegeMessage(new Possession(), 1);
		game.notifyCurrent(privilegeMessage);
	}

}
