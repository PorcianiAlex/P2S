package it.polimi.ingsw.GC_21.ACTION;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Item;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ExcommAction extends Action {
	private boolean choice;
	private int[] faithPointsTracking;
	
	public ExcommAction(Player playerInAction, Game game, boolean choice) {
		super(playerInAction);
		this.choice = choice;
		this.game = game;
		faithPointsTracking = new int[21];
		faithPointsTracking[0] = 1;
		faithPointsTracking[1] = 2;
		faithPointsTracking[2] = 3;
		faithPointsTracking[3] = 4;
		faithPointsTracking[4] = 5;
		faithPointsTracking[5] = 6;
		faithPointsTracking[6] = 8;
		faithPointsTracking[7] = 9;
		faithPointsTracking[8] = 10;
		faithPointsTracking[9] = 11;
		faithPointsTracking[10] = 13;
		faithPointsTracking[11] = 14;
		faithPointsTracking[12] = 15;
		faithPointsTracking[13] = 16;
		faithPointsTracking[14] = 17;
		faithPointsTracking[15] = 18;
		faithPointsTracking[16] = 19;
		faithPointsTracking[17] = 20;
		faithPointsTracking[18] = 21;
		faithPointsTracking[19] = 22;
		faithPointsTracking[20] = 23;


	}	
	
	@Override
	public void Execute() {
		int age = game.getCurrentAge().getAgeNumber();
		int threshold = game.getExcommHandler().getExcommThresholds()[age-1];
		if (this.choice || playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue()<threshold) {
			playerInAction.getMyPersonalBoard().addPermanentEffect(game.getExcommHandler().getExcommunicationCards()[age-1].getSecondaryEffect());
			game.notifyString("Mmmh... Bergoglio is so upset! " +  playerInAction.getName() + " just got excommunicated!!");
		}
		else {
			playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().setValue(0);
			int currentPositionOnTracking = playerInAction.getMyPersonalBoard().getMyPossession().getFaithPoints().getValue();
			int victoryPointsToGet = faithPointsTracking[currentPositionOnTracking];
			playerInAction.getMyPersonalBoard().getMyPossession().addItemToPossession(Item.factoryItem(victoryPointsToGet, ResourceType.VictoryPoints));
			game.notifyString("Well done " + playerInAction.getName() +", you haven't been excommunicated, but you will lose all your faith points!");
			game.notifyString("...But, you'll gain " + victoryPointsToGet + "Victory Points!");

		}
	}
}
