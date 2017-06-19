package it.polimi.ingsw.GC_21.EFFECT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.BooleanSeqHelper;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.CLIENT.PrivilegeMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.PrivilegeInput;

public  class Effect implements Serializable{
	protected Game game; 
	protected Possession rewards;
	protected Privileges privileges;
	protected ArrayList<Possession> earnedRewards;

	public Effect(Possession rewards, int privileges, Game game) {
		this.game=game;
		this.rewards = rewards;
		if (rewards == null){
			this.rewards = new Possession();
		}
		this.earnedRewards = new ArrayList<Possession>();
		this.privileges = new Privileges(privileges);
	}
	
	public Effect(Possession rewards, int privileges, Game game, ArrayList<Possession> earnedRewards) {
		this.game=game;
		this.rewards = rewards;
		if (rewards == null){
			this.rewards = new Possession();
		}
		this.earnedRewards = earnedRewards;
		this.privileges = new Privileges(privileges);
	}


	public Effect(){
		this.rewards = new Possession();
		this.privileges = new Privileges(0);
		this.earnedRewards = new ArrayList<Possession>();
	}
	
	
	public void activateEffect(Player player, Action action) {
		if (player!=null){
			if (privileges.getValue()!=0){
				PrivilegeMessage privilegeMessage = new PrivilegeMessage(rewards, privileges.getValue(), earnedRewards);
				game.notifyCurrent(privilegeMessage);
				}
		else{
			this.callWhenEarningEffects(player, action);
			this.earnByPrivilege(player);
			this.earnRewards(player, rewards);
			if (!rewards.equals(new Possession())){
				game.notifyCurrentString("Oh ragazzotto! Here's a little cantuccino for you:\n" + rewards.toString());
			}
		}
	}
	}

	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Privileges getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Privileges privileges) {
		this.privileges = privileges;
	}

	public ArrayList<Possession> getEarnedRewards() {
		return earnedRewards;
	}

	public void setEarnedRewards(ArrayList<Possession> earnedRewards) {
		this.earnedRewards = earnedRewards;
	}

	public void setRewards(Possession rewards) {
		this.rewards = rewards;
	}

	public void earnByPrivilege(Player player) {
		if (!earnedRewards.isEmpty())
			for (int i = 0; i < earnedRewards.size(); i++) {
				Possession toEarnByPrivilege = earnedRewards.get(i);
				this.earnRewards(player, toEarnByPrivilege);
			}
	}
	
	public void setEarnedReward(Possession reward) {
		earnedRewards.add(reward);
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