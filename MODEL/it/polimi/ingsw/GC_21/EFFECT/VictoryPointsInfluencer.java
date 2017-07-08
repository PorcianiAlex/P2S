package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Item;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class VictoryPointsInfluencer extends Effect implements ToCallAfterFinalCount, Permanent{
	private final ResourceType forEachResource;
	private final int forEachResourceIndex;
	private final int losingIndex;
	private boolean eachResource;
	
	public VictoryPointsInfluencer(Game game, ResourceType forEachResource, int forEachResourceIndex, int losingIndex, boolean eachResource) {
		this.forEachResource = forEachResource;
		this.forEachResourceIndex = forEachResourceIndex;
		this.losingIndex = losingIndex;
		this.eachResource = eachResource;
		this.game=game;
	}

	@Override
	public void activateEffect(Player player, Action action) {
		if (!eachResource){
			int playerResourceNumber = player.getMyPersonalBoard().getMyPossession().getRequestedItem(forEachResource).getValue();
			int resourceMultiplier = playerResourceNumber/forEachResourceIndex;
			Possession malus = new Possession(0,0,0,0,0,0,0);
			Item victoryPointsToLose = Item.factoryItem(resourceMultiplier, ResourceType.VictoryPoints);
			malus.addItemToPossession(victoryPointsToLose);
			//game.notifyCurrentString("You will lose " + losingIndex +" Victory Points for each " + forEachResourceIndex + " " + forEachResource + " you have!");
			player.getMyPersonalBoard().payPossession(malus);
		}
		else{ //If I've to pay for each resource I have got
			//game.notifyCurrentString("You will lose " + losingIndex + " Victory Points, for every Coins, Stones, Woods or Servants!");
			int servantsNumber = player.getMyPersonalBoard().getMyPossession().getServants().getValue();
			int coinsNumber = player.getMyPersonalBoard().getMyPossession().getCoins().getValue();
			int woodsNumber = player.getMyPersonalBoard().getMyPossession().getWoods().getValue();
			int stonesNumber = player.getMyPersonalBoard().getMyPossession().getStones().getValue();
			int sumOfResources = servantsNumber + coinsNumber + woodsNumber + stonesNumber;
			Possession malus = new Possession(0,0,0,0,0,0,0);
			Item victoryPointsToLose = Item.factoryItem(sumOfResources, ResourceType.VictoryPoints);
			malus.addItemToPossession(victoryPointsToLose);
			player.getMyPersonalBoard().payPossession(malus);
		}
	}
	
}
