package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public abstract class Effect {
	protected Possession rewards;
	
	public Effect(Possession rewards) {
		this.rewards = rewards;
	}

	public void activateEffect(Player player) {
		if (rewards.getPrivileges().getValue()!=0){
			ConvertPrivilege convertPrivilege = new ConvertPrivilege(rewards);
			convertPrivilege.activateEffect(player);
		}
		this.earnRewards(player, rewards);

	}
	
	public void payAndEarn(Player player, Possession rewards, Possession payment){
		player.getMyPersonalBoard().payPossession(payment);
		earnRewards(player, rewards);
	}
	
	public void earnRewards(Player player, Possession rewards){
		player.getMyPersonalBoard().getMyPossession().add(rewards);
	}
	
	public Possession getRewards() {
		return rewards;
	}
}