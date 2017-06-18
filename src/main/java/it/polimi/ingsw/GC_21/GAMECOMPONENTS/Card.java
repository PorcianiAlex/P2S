package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.io.Serializable;
import java.util.*;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.EFFECT.*;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class Card implements Serializable{

	protected final String name;
	protected Possession requirements;
	private Effect immediateEffect;
	private Effect secondaryEffect;

	public Card(String name) {
		this.name=name;
	}
	

	public String getName() {
		return name;
	}


	public Possession getRequirements() {
		return requirements;
	}



	public void setRequirements(Possession requirements) {
		this.requirements = requirements;
	}



	public void callCraftEffect(Player player){
		secondaryEffect.activateEffect(player, null);
	}
	
	
	public void callEffect(EffectType effectType, Player player, Action action) {
		if(effectType.equals(EffectType.Immediate)) {
			immediateEffect.activateEffect(player, action);
		}
	}



	public Effect getImmediateEffect() {
		return immediateEffect;
	}



	public Effect getSecondaryEffect() {
		return secondaryEffect;
	}


	public void setSecondaryEffect(Effect secondaryEffect) {
		this.secondaryEffect = secondaryEffect;
	}


	public void setImmediateEffect(Effect immediateEffect) {
		this.immediateEffect = immediateEffect;
	}



	
	@Override
	public String toString() {
		return "Card [name=" + name + "]";
	}

	
	
}