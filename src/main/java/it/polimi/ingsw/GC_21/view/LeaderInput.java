package it.polimi.ingsw.GC_21.view;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.ACTION.LeaderAction;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;

public class LeaderInput extends InputForm{
	
	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		adapterConnection.out("Do you want to turn a Leader Card or to play one of your activated Leader Card?");
		adapterConnection.out("\n(1) Turn Leader Card");
		adapterConnection.out("\n(2) Play Leader Card");
		String choice = adapterConnection.in();
		switch (choice){
		case "1":	LeaderCard leaderToTurn = this.chooseLeaderToTurn(remoteView.getPlayer().getLeaderCards());
					LeaderAction leaderTurn = new LeaderAction(remoteView.getPlayer(), leaderToTurn,  true, remoteView.getGame());
					remoteView.response(leaderTurn);
					break;
		case "2": 	LeaderCard leaderToPlay = this.chooseLeaderToPlay(remoteView.getPlayer().getPlayedOncePerTurnLeaderCards());
					if (leaderToPlay != null){
						LeaderAction leaderPlay = new LeaderAction(remoteView.getPlayer(), leaderToPlay, false, remoteView.getGame());
						remoteView.response(leaderPlay);}
					else{
						remoteView.input();
					}
					break;
		default: adapterConnection.out("Invalid choice, try again!");
					remoteView.input();
		}


	}
	
	private LeaderCard chooseLeaderToPlay(ArrayList<OncePerTurnLeaderCard> playedOncePerTurnLeaderCards) {
		if (playedOncePerTurnLeaderCards.isEmpty()){
			adapterConnection.out("You didn't play any Leader Card yet or none of your Leader Cards can help you now!");
			return null;
		}
		else{
			for (int i = 0; i < playedOncePerTurnLeaderCards.size(); i++) {
				adapterConnection.out("Insert " +"(" + i+1 + ") to play " + playedOncePerTurnLeaderCards.get(i).getName());
			}
			int leaderToPlay = Integer.parseInt(adapterConnection.in());
			if (leaderToPlay < playedOncePerTurnLeaderCards.size()){
				return playedOncePerTurnLeaderCards.get(leaderToPlay-1);
			}
			else {
				adapterConnection.out("Invalid choice, try again!");
				return null;
			}
		}
	}

	public LeaderCard chooseLeaderToTurn(ArrayList<LeaderCard> leaderCards){
		adapterConnection.out("Which leader card do you want to activate? /n (1) " + leaderCards.get(0) + "(2) " + leaderCards.get(1) + "(3) " + leaderCards.get(2) );
		String choice = adapterConnection.in();
		switch(choice){
		case "1": return leaderCards.get(0);	
		case "2": return leaderCards.get(1);
		case "3": return leaderCards.get(2);
		default: adapterConnection.out("Invalid choice, try again!");
				return this.chooseLeaderToTurn(leaderCards);
		}
	}
	
}
