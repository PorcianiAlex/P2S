package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ExcommAction extends Action {
	private boolean choice;
	private Game game;
	
	public ExcommAction(Player playerInAction, Game game, boolean choice) {
		super(playerInAction);
		this.choice = choice;
		this.game = game;
	}	
	
	@Override
	public void Execute() {
		int age = game.getCurrentAge().getAgeNumber();
		int threshold = game.getExcommHandler().getExcommThresholds()[age-1];
		if (this.choice==true || playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue()>threshold) {
			playerInAction.getMyPersonalBoard().addPermanentEffect(game.getExcommHandler().getExcommunicationCards()[age-1].getSecondaryEffect());
		}
		else {
			playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().setValue(0);
		}
	}
}
