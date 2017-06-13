package it.polimi.ingsw.GC_21.view;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.PrivilegeMessage;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PrivilegeInput extends InputFromView {
	int privilegesNumber;
	protected Possession rewards;
	protected ArrayList<Possession> earnedRewards;

	
	
	public PrivilegeInput(Possession rewards, int privilegesNumber, ArrayList<Possession> earnedRewards) {
		super();
		this.rewards = rewards;
		this.privilegesNumber = privilegesNumber;
		this.earnedRewards = earnedRewards;
	}

	@Override
	public void execute(RemoteView remoteView){
		super.execute(remoteView);
		adapterConnection.out("You have " + privilegesNumber + " privileges left to convert!");
		adapterConnection.out("Choose your reward! Type: \n (1) 1x Woods 1 x Stones \n (2) 2x Servants \n (3) 2x Coins \n (4) 2x Military Points \n (5) 1x Faith Points");
		String choice = remoteView.getAdapter().in();
		PrivilegeMessage rewardMessage = new PrivilegeMessage(choice, rewards, privilegesNumber, earnedRewards);
		if (!remoteView.notifyMessage(rewardMessage)){
			this.execute(remoteView);
			}
		}
	}


