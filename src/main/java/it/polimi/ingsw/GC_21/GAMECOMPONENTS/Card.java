package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

import java.util.*;
import it.polimi.ingsw.GC_21.EFFECT.*;

public abstract class Card {

	private String name;
	private Possesion requirements;
	private Permanent permanentEffect ;
	private Immediate immediateEffect;
	


	
	
	public void callEffect(EffectType effectType) {
		if(effectType.equals("Immediate")) {
			immediateEffect.activateEffect();
		}
		else {
			permanentEffect.activateEffect();
		}
	}

}