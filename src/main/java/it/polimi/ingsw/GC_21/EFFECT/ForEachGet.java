package it.polimi.ingsw.GC_21.EFFECT;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.*;
import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

import org.omg.PortableServer.Servant;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Item;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.ResourceType;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ForEachGet extends Immediate{
	private final DevCardType forEachCard;
	private final int forEachCardIndex;
	private final ResourceType forEachResource;
	private final int forEachResourceIndex;
	private final ResourceType resourceYouGet;
	private final int GettingIndex;
	private final boolean CardConversion; //If TRUE, the effect converts cards, otherwise Items
	
	
	/*This effect is supposed to convert card OR resource, not both.
	So one of forEachCard and forEachResource MUST be set to NULL*/
	public ForEachGet(Possession rewards, DevCardType forEachCard, int forEachCardIndex, ResourceType forEachResource,
			int forEachResourceIndex, ResourceType resourceYouGet, int gettingIndex, boolean cardConversion) {
		super(rewards);
		this.forEachCard = forEachCard;
		this.forEachCardIndex = forEachCardIndex;
		this.forEachResource = forEachResource;
		this.forEachResourceIndex = forEachResourceIndex;
		this.resourceYouGet = resourceYouGet;
		GettingIndex = gettingIndex;
		CardConversion = cardConversion;
	}

	public ForEachGet(Possession rewards, ResourceType forEachResource,
			int forEachResourceIndex, ResourceType resourceYouGet, int gettingIndex, boolean cardConversion) {
		super(rewards);
		this.forEachCard = null;
		this.forEachCardIndex = 0;
		this.forEachResource = forEachResource;
		this.forEachResourceIndex = forEachResourceIndex;
		this.resourceYouGet = resourceYouGet;
		GettingIndex = gettingIndex;
		CardConversion = cardConversion;
	}


	@Override
	/* It creates a possession through the bonus of the card and then the player gets it*/
	public void activateEffect(Player player) {
		super.activateEffect(player);
		if (CardConversion == true){
			int playerCardNumber = player.getMyPersonalBoard().getOwnedCards(forEachCard).getOwnedCardsnumber();
			int cardMultiplier = playerCardNumber/forEachCardIndex;
			Possession rewards = new Possession(0,0,0,0,0,0,0);
			Item rewardItem = Item.factoryItem(cardMultiplier * GettingIndex, resourceYouGet);
			rewards.addItemToPossession(rewardItem);
			earnRewards(player, rewards);
		}
		else{
			int playerResourceNumber = player.getMyPersonalBoard().getMyPossession().getRequestedItem(forEachResource).getValue();
			int resourceMultiplier = playerResourceNumber/forEachResourceIndex;
			Possession rewards = new Possession(0,0,0,0,0,0,0);
			Item rewardItem = Item.factoryItem(resourceMultiplier * GettingIndex, resourceYouGet);
			rewards.addItemToPossession(rewardItem);
			earnRewards(player, rewards);
		}
		
	}
	
	public static void main(String[] args) {
		Player aaa = new Player("AA", "AAA");
		Possession aaas = new Possession(100, 111, 111, 212, 321, 2121, 444);
		aaa.getMyPersonalBoard().getMyPossession().add(aaas);
		Possession rewards = new Possession(0, 0, 0, 0, 0, 0, 0);
		ForEachGet forEachGet = new ForEachGet(rewards, ResourceType.Woods, 2, ResourceType.Coins, 1, false);
		forEachGet.activateEffect(aaa);
		System.out.println(aaa.getMyPersonalBoard().getMyPossession().toString());
	}

}
