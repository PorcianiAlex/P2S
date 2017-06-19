package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;
import it.polimi.ingsw.GC_21.CLIENT.PrivilegeMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.PrivilegeInput;

public class DiscardLeaderCard extends Action{
	private LeaderCard leaderChosen;
	
	public DiscardLeaderCard(Player playerInAction, LeaderCard leaderChosen) {
		super(playerInAction);
		this.leaderChosen = leaderChosen;
	}



	@Override
	public void Execute() {
		playerInAction.getMyPersonalBoard().getLeaderCards().remove(leaderChosen);
		PrivilegeMessage privilegeMessage = new PrivilegeMessage(new Possession(), 1, null);
		game.notifyCurrent(privilegeMessage);
	}

}
