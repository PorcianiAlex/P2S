package it.polimi.ingsw.GC_21.BOARD;

import java.util.*;

import it.polimi.ingsw.GC_21.EFFECT.Convert;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Privileges;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class MarketArea {
	private SingleActionSpace[] singleActionSpace;


	public MarketArea(){
		this.singleActionSpace = new SingleActionSpace[4];
		singleActionSpace[0] = new SingleActionSpace(1, new Immediate(new Possession(5, 0, 0, 0, 0, 0, 0),0));
		singleActionSpace[1] = new SingleActionSpace(1, new Immediate(new Possession(0, 0, 0, 5, 0, 0, 0),0));
		singleActionSpace[2] = new SingleActionSpace(1, new Immediate(new Possession(2, 0, 0, 0, 3, 0, 0),0));
		singleActionSpace[3] = new SingleActionSpace(1, new Immediate(null, (2)));
	}

	public SingleActionSpace[] getSingleActionSpace() {
		return singleActionSpace;
	}

	public void setSingleActionSpace(SingleActionSpace[] singleActionSpace) {
		this.singleActionSpace = singleActionSpace;
	}

	public void callImmediateEffect() {
		// TODO - implement MarketArea.callImmediateEffect
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return "MarketArea:" + Arrays.toString(singleActionSpace) + "]";
	}

	
	
}