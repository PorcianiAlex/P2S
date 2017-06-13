package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.view.SetFamilyMemberInput;

public class SetFamilyMemberValue extends Effect {
	private int newFamilyMemberValue;
	
	@Override
	public void activateEffect(Player player, Action action) {
		super.activateEffect(player, action);
		SetFamilyMemberInput setFamilyMemberInput = new SetFamilyMemberInput(newFamilyMemberValue, player);
		game.notifyCurrent(setFamilyMemberInput);
	}

}
