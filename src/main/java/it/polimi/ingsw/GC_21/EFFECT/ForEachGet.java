package it.polimi.ingsw.GC_21.EFFECT;

import javax.annotation.Resource;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ForEachGet extends Immediate{
	private final DevCardType forEachCard;
	private final int forEachCardIndex;
	private final ResourceType forEachResource;
	private final int forEachResourceIndex;
	private final boolean CardConversion; //If TRUE, the effect converts cards, otherwise Items
	
	@Override
	public void activateEffect(Player player) {
		if (CardConversion = true){
			int CardMultiplier = player.getMyPersonalBoard().
		}
	}
	
	

}
