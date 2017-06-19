package it.polimi.ingsw.GC_21.EFFECT;

import java.awt.Choice;
import java.util.Scanner;
import java.util.function.IntPredicate;

import org.junit.internal.Throwables;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.CLIENT.ConvertMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Item;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.ConvertInput;


public class Convert extends Effect implements ToCallDuringCraft{
	private Possession toPay1;
	private Possession toTake1;
	private Possession toPay2;
	private Possession toTake2;
	
	public Convert(Game game, Possession rewards, Possession toPay1, Possession toTake1, Possession toPay2, Possession toTake2, int privileges) {
		super(rewards, privileges, game);
		this.toPay1 = toPay1;
		this.toTake1 = toTake1;
		this.toPay2 = toPay2;
		this.toTake2 = toTake2;
	}

	@Override
	public void activateEffect(Player player,Action action){
		super.activateEffect(player, action);
		if (!toPay2.equals(new Possession()) && !toTake2.equals(new Possession())){ //if there's a second conversion
			ConvertMessage convertMessage = new ConvertMessage(toPay1, toTake1, toPay2, toTake2);
			game.notifyCurrent(convertMessage);
		}
		else{
			if (toPay1.checkRequirements(player)){
				payAndEarn(player, toTake1, toPay1);
			}
			else{
				game.notifyCurrentString("You don't have enough resources to convert!");
				return;
			}
		}
	}

	public Possession getToPay1() {
		return toPay1;
	}


	public Possession getToTake1() {
		return toTake1;
	}


	public Possession getToPay2() {
		return toPay2;
	}


	public Possession getToTake2() {
		return toTake2;
	}


	@Override
	public String toString() {
		if (toPay2!=null && toTake2!=null){
		{
		return "This effect gives the following reward=" + rewards.toString() + "]"+
		" It converts " + toPay1.toString() + " into " + toTake1.toString() + " or " + toPay2.toString() + " into " + toTake2.toString();}
		}
		else {
			return "This effect gives the following reward=" + rewards.toString() + "]" + 
					"It converts " + toPay1.toString() + " into" + toTake1.toString();
		}
		
		}
	

}