package it.polimi.ingsw.GC_21.BOARD;

import java.util.*;

import it.polimi.ingsw.GC_21.EFFECT.Convert;
import it.polimi.ingsw.GC_21.EFFECT.DoTakeCardAction;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Privileges;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;

public class MarketArea {
	private SingleActionSpace[] singleActionSpace;
	private Game game;

	public MarketArea(Game game){
		this.singleActionSpace = new SingleActionSpace[4];
		this.game=game;
		singleActionSpace[0] = new SingleActionSpace(1, new DoTakeCardAction(game, null, 0, 4, null, new Possession()), game);
		//singleActionSpace[0] = new SingleActionSpace(1, new Immediate(new Possession(2, 0, 0, 0, 0, 0, 0),0, game), game);
		singleActionSpace[1] = new SingleActionSpace(1, new DoTakeCardAction(game, null, 0, 2, DevCardType.Building, new Possession()), game);
		//singleActionSpace[1] = new SingleActionSpace(1, new Immediate(new Possession(0, 0, 0, 5, 0, 0, 0),0, game), game);
		singleActionSpace[2] = new SingleActionSpace(1, new Immediate(new Possession(2, 0, 0, 0, 0, 3, 0),0, game),game);
		singleActionSpace[3] = new SingleActionSpace(1, new Immediate(null, (2), game), game);
	}

	public SingleActionSpace[] getSingleActionSpace() {
		return singleActionSpace;
	}

	public void setSingleActionSpace(SingleActionSpace[] singleActionSpace) {
		this.singleActionSpace = singleActionSpace;
	}

	@Override
	public String toString() {
		return "MarketArea:" + Arrays.toString(singleActionSpace) + "]";
	}

	
	
}