package it.polimi.ingsw.GC_21.EFFECT;

import java.awt.image.AreaAveragingScaleFilter;

import javax.sound.midi.Soundbank;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.PersonalBoard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public  class Effect {
	protected Possession rewards;
	
	//TO DO: IMPLEMENT INFLUENCE ON ACTION; THE ACTIVATE MUST TAKE THE ACTION IN INPUT TOO!!
	
	public Effect(Possession rewards) {
		this.rewards = rewards;
	}

	public Effect(Possession rewards, int actionValueInfluencer) {
		this.rewards = rewards;
	}

	
	public void activateEffect(Player player, Action action) {
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

	@Override
	public String toString() {
		return "This effect gives the following reward=" + rewards.toString() + "]";
	}
	
	public static void main(String[] args) {
		Possession aaa = new Possession(3, 2, 1, 22, 2, 1, 1);
		Effect aaas = new Effect(aaa);
		System.out.println(aaas.toString());
		Player provaPlayer = new Player("Prova", "Red");
		System.out.println(provaPlayer.toString());
		System.out.println(provaPlayer.getMyPersonalBoard().getMyPossession().toString());
	}
}