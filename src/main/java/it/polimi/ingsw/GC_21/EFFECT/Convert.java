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

}