package it.polimi.ingsw.GC_21.VIEW;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;
import it.polimi.ingsw.GC_21.CLIENT.PrivilegeMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class DiscardInput extends InputForm {
	private LeaderCard leaderToDiscard;
	private ArrayList<LeaderCard> leaderCards;
	
	

	public DiscardInput(Player player) {
		super();
		this.leaderCards = player.getMyPersonalBoard().getLeaderCards();
	}

	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		if (leaderToDiscard.isPlayed() ){
			ChooseActionMessage chooseActionMessage = new ChooseActionMessage("You cannot discard a Leader Card you already played!",  remoteView.getPlayer());
			remoteView.getAdapter().sendObject(chooseActionMessage);
		}
		
		else{
			remoteView.getPlayer().getMyPersonalBoard().getLeaderCards().remove(leaderToDiscard);
			PrivilegeMessage privilegeMessage = new PrivilegeMessage(new Possession(), 1, new ArrayList<Possession>());
			remoteView.getAdapter().sendObject(privilegeMessage);
			//remoteView.inputObject();
		}
	}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		System.out.println("Which Leader Card do you want to discard?\n"
				+ "You can only discard a leader you did not play yet, but you'll get 1 privilege!");
		for (int i = 0; i < leaderCards.size(); i++) {
			int toPrint = i+1;
			System.out.println("Insert " +"(" + toPrint + ") to discard " + leaderCards.get(i).getName());
		}
		try {
			int choice = Integer.parseInt(keyboard.next());
			leaderToDiscard = leaderCards.get(choice-1);
		} catch (Exception e) {
			System.out.println("Invalid input, try again!");
			this.inputFromCli(keyboard);
		}
	}

}
