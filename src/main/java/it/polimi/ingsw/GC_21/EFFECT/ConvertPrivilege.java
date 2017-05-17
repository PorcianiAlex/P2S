package it.polimi.ingsw.GC_21.EFFECT;

import java.awt.HeadlessException;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Privileges;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ConvertPrivilege extends Immediate {
	private Privileges privileges;
	private boolean coinsEarned;
	private boolean woodsAndStonesEarned;
	private boolean servantsEarned;
	private boolean militaryPointsEarned;
	private boolean faithPointsEarned;
	
	public ConvertPrivilege(Possession rewards, Privileges privileges) {
		super(rewards);
		this.privileges = privileges;
	}

	@Override
	public void activateEffect(Player player) {
		super.activateEffect(player);
		/* The method ask the player to choose the reward, if it's valid then 
		he gets it, if it's not then another cycle is done*/
		for (int i = privileges.getValue(); i > 0; i--) {
			Possession tmpPossession = this.chooseReward(player);
			if (validConversion(tmpPossession) == true ){
				earnRewards(player, tmpPossession);
				//TODO: se prendo una possession devo settare il rispettivo boolean a true e poi devo gestire il controllo!!
			}
			else {
				i++;
			}
			}
		}
	

	
	
	public Possession chooseReward(Player player) {
		return privileges.getCoinsReward();
	}
	
	public boolean validConversion(Possession possession){
		
	}

}