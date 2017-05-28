package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.*;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.EFFECT.*;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public  class Card {

	private String name;
	private Possession requirements;
	private Immediate immediateEffect;
	private Effect secondaryEffect;

	public Card(String name) {
		this.name=name;
	}
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Possession getRequirements() {
		return requirements;
	}



	public void setRequirements(Possession requirements) {
		this.requirements = requirements;
	}




	public Card(String name, Possession requirements, Immediate immediateEffect, Effect secondaryEffect) {
		super();
		this.name = name;
		this.requirements = requirements;
		this.immediateEffect = immediateEffect;
		this.secondaryEffect = secondaryEffect;
	}


	public Immediate getImmediateEffect() {
		return immediateEffect;
	}



	public void setImmediateEffect(Immediate immediateEffect) {
		this.immediateEffect = immediateEffect;
	}



	public void callEffect(EffectType effectType, Player player, Action action) {
		if(effectType.equals("Immediate")) {
			immediateEffect.activateEffect(player, action);
		}
		else {
			permanentEffect.activateEffect(player, action);
		}
	}


	@Override
	public String toString() {
		return "Card [name=" + name + "]";
	}

	
	
}