package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;

import java.util.IntSummaryStatistics;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

import org.omg.PortableServer.Servant;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Item;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ForEachGet extends Effect implements ToCallDuringCraft{
	private final DevCardType forEachCard;
	private final int forEachCardIndex;
	private final ResourceType forEachResource;
	private final int forEachResourceIndex;
	private final ResourceType resourceYouGet;
	private final int gettingIndex;
	private final boolean CardConversion; //If TRUE, the effect converts cards, otherwise Items
	
	
	/*This effect is supposed to convert card OR resource, not both.
	So one of forEachCard and forEachResource MUST be set to NULL*/
	public ForEachGet(Game game, Possession rewards, int privileges, DevCardType forEachCard, int forEachCardIndex, ResourceType forEachResource,
			int forEachResourceIndex, ResourceType resourceYouGet, int gettingIndex, boolean cardConversion){
		super(rewards, privileges, game);
		this.forEachCard = forEachCard;
		this.forEachCardIndex = forEachCardIndex;
		this.forEachResource = forEachResource;
		this.forEachResourceIndex = forEachResourceIndex;
		this.resourceYouGet = resourceYouGet;
		this.gettingIndex = gettingIndex;
		CardConversion = cardConversion;
	}



	@Override
	/* It creates a possession through the bonus of the card and then the player gets it*/
	public void activateEffect(Player player, Action action) {
		super.activateEffect(player, action);
		if (CardConversion){
			int playerCardNumber = player.getMyPersonalBoard().getSpecificOwnedCards(forEachCard).getOwnedCardsnumber();
			int cardMultiplier = playerCardNumber/forEachCardIndex;
			Possession rewards = new Possession(0,0,0,0,0,0,0);
			Item rewardItem = Item.factoryItem(cardMultiplier * gettingIndex, resourceYouGet);
			rewards.addItemToPossession(rewardItem);
			callWhenEarningEffects(player, action);
			game.notifyCurrentString("You will get " + gettingIndex + "x" + resourceYouGet + "for each" + forEachResourceIndex + " " + forEachResource);
			earnRewards(player, rewards);
		}
		else{
			int playerResourceNumber = player.getMyPersonalBoard().getMyPossession().getRequestedItem(forEachResource).getValue();
			int resourceMultiplier = playerResourceNumber/forEachResourceIndex;
			Possession rewards = new Possession(0,0,0,0,0,0,0);
			Item rewardItem = Item.factoryItem(resourceMultiplier * gettingIndex, resourceYouGet);
			rewards.addItemToPossession(rewardItem);
			callWhenEarningEffects(player, action);
			game.notifyCurrentString("You will get " + gettingIndex + "x" + resourceYouGet + "for each" + forEachCardIndex + " " + forEachCard);
			earnRewards(player, rewards);
		}
		
	}
	
	
}
