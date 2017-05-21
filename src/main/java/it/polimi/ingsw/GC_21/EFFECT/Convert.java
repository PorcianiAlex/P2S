package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Item;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class Convert extends Immediate {
	private final Possession toPay1;
	private final Possession toTake1;
	private final Possession toPay2;
	private final Possession toTake2;
	
	public Convert(Possession rewards, Possession toPay1, Possession toTake1, Possession toPay2, Possession toTake2) {
		super(rewards);
		this.toPay1 = toPay1;
		this.toTake1 = toTake1;
		this.toPay2 = toPay2;
		this.toTake2 = toTake2;
	}

	@Override
	public void activateEffect(Player player){
		super.activateEffect(player);
		if (toPay2!=null && toTake2!=null){
			if (this.chooseConversion(player)==true){
				payAndEarn(player, toTake1, toPay1);
			}
			else{
				if (this.chooseConversion(player)==true){
					payAndEarn(player, toTake2, toPay2);
				}
			}
		}
		else{
			payAndEarn(player, toTake1, toPay1);
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


	public boolean chooseConversion(Player player) {
		return true;
	}

	@Override
	public String toString() {
		{
		return "This effect gives the following reward=" + rewards.toString() + "]"+
		"It converts " + toPay1.toString() + "into " + toTake1.toString() + "or " + toPay2.toString() + "into " + toTake2.toString();}
	}
	
	public static void main(String[] args) {
		Possession toPay1 = new Possession(0, 0, 0, 0, 1, 1, 2, 2);
		Possession toTake1 = new Possession(0, 2, 2, 0, 1, 1, 2, 2);
		Possession toPay2 = new Possession(0, 0, 0, 0, 1, 1, 2, 2);
		Possession toTake2 = new Possession(0,11, 0, 0, 1, 1, 2, 2);
		Possession rewards = new Possession(0, 0, 0, 0, 0, 0, 0, 0);
		Convert convert = new Convert(rewards, toPay1, toTake1, toPay2, toTake2);
		System.out.println(convert.toString());
	}

	
	
}