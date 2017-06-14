package it.polimi.ingsw.GC_21.view;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.ACTION.LeaderAction;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;

public class LeaderInput extends InputFromView{
	
	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		adapterConnection.out("Do you want to turn a Leader Card or to play one of your activated Leader Card?");
		adapterConnection.out("/n(1) Turn Leader Card");
		adapterConnection.out("/n(2) Play Leader Card");
		String choice = adapterConnection.in();
		LeaderCard leaderChosen = this.chooseLeaderCard(remoteView.getPlayer().getLeaderCards());
		switch (choice){
		case "1":	LeaderAction leaderAction = new LeaderAction(remoteView.getPlayer(), leaderChosen,  true);
					remoteView.notifyObservers(leaderAction);
					break;
		case "2": 	LeaderAction leaderAction1 = new LeaderAction(remoteView.getPlayer(), leaderChosen, false);
					remoteView.notifyObservers(leaderAction1);
					break;
		default: adapterConnection.out("Invalid choice, try again!");
					remoteView.input();
		}


	}
	
	public LeaderCard chooseLeaderCard(ArrayList<LeaderCard> leaderCards){
		adapterConnection.out("Which leader card do you want to activate? /n (1) " + leaderCards.get(0) + "(2) " + leaderCards.get(1) + "(3) " + leaderCards.get(2) );
		String choice = adapterConnection.in();
		switch(choice){
		case "1": return leaderCards.get(0);	
		case "2": return leaderCards.get(1);
		case "3": return leaderCards.get(2);
		default: adapterConnection.out("Invalid choice, try again!");
				return this.chooseLeaderCard(leaderCards);
		}
	}
	
}
