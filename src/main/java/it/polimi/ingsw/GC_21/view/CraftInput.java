package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftInput extends InputForm {
	private CraftType craftType;
	private int actionValue;
	
	public CraftInput(CraftType craftType, int actionValue) {
		super();
		this.craftType = craftType;
		this.actionValue = actionValue;
	}
	
	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		adapterConnection.out("You can make a craft with value " + actionValue + ", how many servant do you want to convert?");
		int servantsToConvert = Integer.parseInt(adapterConnection.in());
		if (servantsToConvert > remoteView.getPlayer().getMyPersonalBoard().getMyPossession().getServants().getValue()){
			adapterConnection.out("Not enough servants to convert!");
			this.execute(remoteView);
		}
		CraftAction craftAction = new CraftAction(remoteView.getPlayer(), craftType, servantsToConvert);
		remoteView.response(craftAction);
		}

}
