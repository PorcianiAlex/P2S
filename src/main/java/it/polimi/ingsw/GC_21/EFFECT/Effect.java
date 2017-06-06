package it.polimi.ingsw.GC_21.EFFECT;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public  class Effect implements ToCallDuringCraft{
	protected Possession rewards;
	protected Privileges privileges;
	protected boolean coinsEarned;
	protected boolean woodsAndStonesEarned;
	protected boolean servantsEarned;
	protected boolean militaryPointsEarned;
	protected boolean faithPointsEarned;
	protected final Possession woodsAndStonesReward;
	protected final Possession servantsReward;
	protected final Possession coinsReward;
	protected final Possession militaryPointsReward;
	protected final Possession faithPointsReward;
	
	public Effect(Possession rewards, int privileges) {
		this.rewards = rewards;
		if (rewards == null){
			this.rewards = new Possession(0, 0, 0, 0, 0, 0, 0);
		}
		this.privileges = new Privileges(privileges);
		this.woodsAndStonesReward = new Possession(0, 1, 1, 0, 0, 0, 0);
		this.servantsReward = new Possession(0,0,0,2,0,0,0);
		this.coinsReward = new Possession(2, 0, 0, 0, 0, 0, 0);
		this.militaryPointsReward = new Possession(0, 0, 0, 0, 0, 2, 0);
		this.faithPointsReward = new Possession(0, 0, 0, 0, 1, 0, 0);
	}

	public Effect(){
		this.rewards = new Possession();
		this.privileges = new Privileges(0);
		this.woodsAndStonesReward = new Possession(0, 1, 1, 0, 0, 0, 0);
		this.servantsReward = new Possession(0,0,0,2,0,0,0);
		this.coinsReward = new Possession(2, 0, 0, 0, 0, 0, 0);
		this.militaryPointsReward = new Possession(0, 0, 0, 0, 0, 2, 0);
		this.faithPointsReward = new Possession(0, 0, 0, 0, 1, 0, 0);
	}
	
	
	public void activateEffect(Player player, Action action) {
		if (privileges.getValue()!=0){
			for (int i = this.privileges.getValue(); i > 0; i--) {
				Possession tmpPossession = this.chooseReward(player);
				if (validConversion(tmpPossession, player) == true ){
					this.earnRewards(player, tmpPossession);
					setEarnedReward(tmpPossession);	
				}
				else {
					i++;
				}
				}
		}
		this.callWhenEarningEffects(player, action);
		this.resetEarnedReward();
		this.earnRewards(player, rewards);
	}
	
	public void resetEarnedReward() {
		this.coinsEarned=false;
		this.woodsAndStonesEarned=false;
		this.servantsEarned=false;
		this.militaryPointsEarned=false;
		this.faithPointsEarned=false;
	}

	public Possession chooseReward(Player player){
		player.printOnPlayer("Choose your reward! Type: /n 1 -> 1x Woods 1 x Stones /n 2 -> 2x Servants /n 3 -> 2x Coins /n 4 -> 2x Military Points 5 -> 1x Faith Points");
		String choice = player.getMyView().getAdapter().in();
		switch(choice){
		case "1": 
		return this.woodsAndStonesReward;
		case "2": 
		return this.servantsReward;
		case "3": 
		return this.coinsReward;
		case "4":
		return this.militaryPointsReward;
		case "5": 
		return this.faithPointsReward;
		default: 
			player.printOnPlayer("Invalid choice, try again!");
 			return chooseReward(player);
		}
	}
	
	public void setEarnedReward(Possession reward) {
		if (reward.equals(this.woodsAndStonesReward)){
			this.woodsAndStonesEarned=true;
		}
		if (reward.equals(this.coinsReward)){
			this.coinsEarned=true;
		}
		if (reward.equals(this.faithPointsReward)){
			this.faithPointsEarned=true;
		}
		if (reward.equals(this.militaryPointsReward)){
			this.militaryPointsEarned=true;
		}
		if (reward.equals(this.servantsReward)){
			this.servantsEarned=true;
		}
	}
	
	public boolean validConversion(Possession reward, Player player){
		if (reward.equals(this.woodsAndStonesReward) && this.woodsAndStonesEarned==true){
			player.printOnPlayer("You've already chosen this reward! Try again!");
			return false;
		}
		if (reward.equals(this.coinsReward) && this.coinsEarned==true){
			player.printOnPlayer("You've already chosen this reward! Try again!");
			return false;
		}
		if (reward.equals(this.servantsReward) && this.servantsEarned==true){
			player.printOnPlayer("You've already chosen this reward! Try again!");
			return false;
		}
		if (reward.equals(this.militaryPointsReward) && this.militaryPointsEarned==true){
			player.printOnPlayer("You've already chosen this reward! Try again!");
			return false;
		}
		if (reward.equals(this.faithPointsReward) && this.faithPointsEarned==true){
			player.printOnPlayer("You've already chosen this reward! Try again!");
			return false;
		}
		return true;
	}
	
	public void payAndEarn(Player player, Possession rewards, Possession payment){
		player.getMyPersonalBoard().payPossession(payment);
		earnRewards(player, rewards);
	}
	
	public void callWhenEarningEffects(Player player, Action action){
		if (!player.getMyPersonalBoard().getToCallWhenEarningEffects().isEmpty()){
			ArrayList<ToCallWhenEarning> effectsOnTheGo = player.getMyPersonalBoard().getToCallWhenEarningEffects();
			for (int i = 0; i < effectsOnTheGo.size(); i++) {
				((Effect) effectsOnTheGo.get(i)).activateEffect(player, action);
			}
		}
	}
	
	public void earnRewards(Player player, Possession rewards){
		player.getMyPersonalBoard().getMyPossession().add(rewards);
	}
	
	public Possession getRewards() {
		return rewards;
	}

	@Override
	public String toString() {
		return "This effect gives the following reward=" + rewards.toString() + "]";
	}
	
	
}