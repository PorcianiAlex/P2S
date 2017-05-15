package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.*;
import it.polimi.ingsw.GC_21.EFFECT.*;

public  class Card {

	private String name;
	private Possession requirements;
	private Permanent permanentEffect ;
	private Immediate immediateEffect;

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



	public Permanent getPermanentEffect() {
		return permanentEffect;
	}



	public void setPermanentEffect(Permanent permanentEffect) {
		this.permanentEffect = permanentEffect;
	}



	public Immediate getImmediateEffect() {
		return immediateEffect;
	}



	public void setImmediateEffect(Immediate immediateEffect) {
		this.immediateEffect = immediateEffect;
	}



	public void callEffect(EffectType effectType) {
		if(effectType.equals("Immediate")) {
			immediateEffect.activateEffect();
		}
		else {
			permanentEffect.activateEffect();
		}
	}

}