package it.polimi.ingsw.GC_21.VIEW;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.DiscardLeaderCard;
import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;
import it.polimi.ingsw.GC_21.CLIENT.PrivilegeMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DiscardInput extends InputForm {
	private int leaderToDiscard;
	private ArrayList<LeaderCard> leaderCards;
	

	public DiscardInput(Player player) {
		this.leaderCards = player.getMyPersonalBoard().getLeaderCards();
	}

	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		if (leaderCards.isEmpty()){
			ChooseActionMessage chooseActionMessage = new ChooseActionMessage("",  remoteView.getPlayer());
			remoteView.getAdapter().sendObject(chooseActionMessage);
			remoteView.inputObject();
		}
		else if (remoteView.getPlayer().getMyPersonalBoard().getLeaderCards().get(leaderToDiscard).isPlayed() ){

		}
		else if (!remoteView.getPlayer().getMyPersonalBoard().getLeaderCards().contains(leaderToDiscard)){
				DiscardLeaderCard discardLeaderCard = new DiscardLeaderCard(remoteView.getPlayer(), leaderToDiscard, remoteView.getGame());
				discardLeaderCard.Execute();
				ChooseActionMessage chooseActionMessage = new ChooseActionMessage("You just discarded your leader card!",  remoteView.getPlayer());
				remoteView.getAdapter().sendObject(chooseActionMessage);
				remoteView.inputObject();
			}
		
		else{
			ChooseActionMessage chooseActionMessage = new ChooseActionMessage("You already discarded this leader card!", remoteView.getPlayer());
			remoteView.getAdapter().sendObject(chooseActionMessage);
			remoteView.inputObject();
		}
	}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		if (leaderCards.isEmpty()){
			System.out.println("You don't have any leader card to discard!");
		}
		else {
			System.out.println("Which Leader Card do you want to discard?\n"
				+ "You can only discard a leader you did not play yet, but you'll get 1 privilege!");
		for (int i = 0; i < leaderCards.size(); i++) {
			int toPrint = i+1;
			System.out.println("Insert " +"(" + toPrint + ") to discard " + leaderCards.get(i).getName());
		}
		try {
			int choice = Integer.parseInt(keyboard.next());
			leaderToDiscard = choice-1;
		} catch (Exception e) {
			System.out.println("Exception, try again!");
			this.inputFromCli(keyboard);
		}
		}
	}

}
