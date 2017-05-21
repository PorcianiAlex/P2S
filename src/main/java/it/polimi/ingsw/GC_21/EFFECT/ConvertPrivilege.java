package it.polimi.ingsw.GC_21.EFFECT;

import java.nio.channels.NonWritableChannelException;
import java.util.Scanner;

import javax.sound.midi.Soundbank;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Privileges;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ConvertPrivilege extends Immediate {
	private Privileges privileges;
	private boolean coinsEarned;
	private boolean woodsAndStonesEarned;
	private boolean servantsEarned;
	private boolean militaryPointsEarned;
	private boolean faithPointsEarned;
	private final Possession woodsAndStonesReward;
	private final Possession servantsReward;
	private final Possession coinsReward;
	private final Possession militaryPointsReward;
	private final Possession faithPointsReward;
	
	public ConvertPrivilege(Possession rewards, Privileges privileges) {
		super(rewards);
		this.privileges = privileges;
		this.woodsAndStonesReward = new Possession(0, 1, 1, 0, 0, 0, 0, 0);
		this.servantsReward = new Possession(0,0,0,2,0,0,0,0);
		this.coinsReward = new Possession(2, 0, 0, 0, 0, 0, 0, 0);
		this.militaryPointsReward = new Possession(0, 0, 0, 0, 0, 2, 0, 0);
		this.faithPointsReward = new Possession(0, 0, 0, 0, 1, 0, 0, 0);
	}

	@Override
	public void activateEffect(Player player) {
		/* The method ask the player to choose the reward, if it's valid then 
		he gets it, if it's not then another cycle is done*/
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
	
	@Override
	public String toString() {
		return "This effect gives the following reward=" + rewards.toString() + ". Now you have:" + privileges.getValue() + " privileges. Convert them!]";
	}
	
	public static void main(String[] args) {
		Possession rewards = new Possession(0, 0, 0, 0, 0, 11, 1, 1);
		Privileges privileges = new Privileges(3);
		ConvertPrivilege convertPrivilege = new ConvertPrivilege(rewards, privileges);
		System.out.println(convertPrivilege.toString());
	}

}