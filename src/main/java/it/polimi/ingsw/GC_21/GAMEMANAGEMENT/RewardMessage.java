package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.rmi.Remote;
import java.util.ArrayList;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class RewardMessage extends Message{
	private String choice;
	private int privileges;
	protected Possession rewards;
	protected ArrayList<Possession> earnedRewards;
	protected Possession rewardByPrivilege;
	
	public RewardMessage(String choice, Possession rewards, int privileges, ArrayList<Possession> earnedRewards) {
		super();
		this.privileges = privileges;
		this.choice = choice;
		this.earnedRewards = earnedRewards;
		this.rewardByPrivilege = createReward(choice);
		this.rewards = rewards;
	}

	private Possession createReward(String choice) {
		switch (choice){
		case "1": return new Possession(0, 1, 1, 0, 0, 0, 0);
		case "2": return new Possession(0, 0, 0, 2, 0, 0, 0);
		case "3": return new Possession(2, 0, 0, 0, 0, 0, 0);
		case "4": return new Possession(0, 0, 0, 0, 0, 2, 0);
		case "5": return new Possession(0, 0, 0, 0, 1, 0, 0);
		default: return new Possession();
		}

	}

	@Override
	public boolean convert() {
		if (validConversion()){
			Effect copyEffect = new Effect(rewards, privileges-1, controller.getModelGame(), earnedRewards);
			copyEffect.setEarnedReward(rewardByPrivilege);
			copyEffect.activateEffect(controller.getRemoteView().getPlayer(), null);
			return true;
		}
		else{
			return false;
		}
	}
	
	

	public boolean validConversion(){
		for (int j = 0; j < earnedRewards.size(); j++) {
			if (earnedRewards.get(j).equals(rewardByPrivilege)){
			return false;
			}
		}
		return true;
		}
	}
	

