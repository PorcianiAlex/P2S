package it.polimi.ingsw.GC_21.EFFECT;

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
	
	//TO DO: IMPLEMENT INFLUENCE ON ACTION; THE ACTIVATE MUST TAKE THE ACTION IN INPUT TOO!!
	
	
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
				Possession tmpPossession = this.chooseReward();
				if (validConversion(tmpPossession) == true ){
					this.rewards.add(tmpPossession);
					setEarnedReward(tmpPossession);	
				}
				else {
					i++;
				}
				}
		}
		this.earnRewards(player, rewards);
	}
	public Possession chooseReward(){
		Scanner in = new Scanner(System.in);
		System.out.println("Choose your reward!Type:");
		System.out.println("1 -> 1x Woods 1x Stones");
		System.out.println("2 -> 2x Servants");
		System.out.println("3 -> 2x Coins");		
		System.out.println("4 -> 2x Military Points");
		System.out.println("5 -> 1x Faith Points");
		int choice = in.nextInt();
		switch(choice){
		case 1: choice = 1;
		return this.woodsAndStonesReward;
		case 2: choice = 2;
		return this.servantsReward;
		case 3: choice = 3;
		return this.coinsReward;
		case 4: choice = 4;
		return this.militaryPointsReward;
		case 5: choice = 5;
		return this.faithPointsReward;
		default: 
			System.out.println("Invalid choice! Try again!");
			return chooseReward();
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
	
	public boolean validConversion(Possession reward){
		if (reward.equals(this.woodsAndStonesReward) && this.woodsAndStonesEarned==true){
			System.out.println("You've already choosen this reward! Try again!");
			return false;
		}
		if (reward.equals(this.coinsReward) && this.coinsEarned==true){
			System.out.println("You've already choosen this reward! Try again!");
			return false;
		}
		if (reward.equals(this.servantsReward) && this.servantsEarned==true){
			System.out.println("You've already choosen this reward! Try again!");
			return false;
		}
		if (reward.equals(this.militaryPointsReward) && this.militaryPointsEarned==true){
			System.out.println("You've already choosen this reward! Try again!");
			return false;
		}
		if (reward.equals(this.faithPointsReward) && this.faithPointsEarned==true){
			System.out.println("You've already choosen this reward! Try again!");
			return false;
		}
		return true;
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

	@Override
	public String toString() {
		return "This effect gives the following reward=" + rewards.toString() + "]";
	}
	
	
}