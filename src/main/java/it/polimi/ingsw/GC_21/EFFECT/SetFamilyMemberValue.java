package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.CLIENT.SetFamilyMemberMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.SetFamilyMemberInput;

public class SetFamilyMemberValue extends Effect {
	private int newFamilyMemberValue;
	
	
	public SetFamilyMemberValue(Possession rewards, int privileges, Game game, int newFamilyMemberValue) {
		super(rewards, privileges, game);
		this.newFamilyMemberValue = newFamilyMemberValue;
	}


	@Override
	public void activateEffect(Player player, Action action) {
		super.activateEffect(player, action);
		SetFamilyMemberMessage setFamilyMemberMessage = new SetFamilyMemberMessage(newFamilyMemberValue, player);
		game.notifyCurrent(setFamilyMemberMessage);
	}

}
