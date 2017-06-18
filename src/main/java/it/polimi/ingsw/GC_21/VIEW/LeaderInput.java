package it.polimi.ingsw.GC_21.VIEW;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.LeaderAction;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class LeaderInput extends InputForm{//TODO to correct
	private Player player;
	private ArrayList<LeaderCard> leaderCards = player.getLeaderCards();
	private ArrayList<OncePerTurnLeaderCard> playedOncePerTurnLeaderCards = player.getPlayedOncePerTurnLeaderCards();
	private String leaderCard;
	private boolean playOrTurn; 
		

	
	public LeaderInput(Player player) {
		this.player = player;
	}

	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		LeaderCard selectedLeaderCard;
		if (playOrTurn) {
			switch(leaderCard){//Change the selection of the card
			case "1":  selectedLeaderCard = leaderCards.get(0);	
			case "2":  selectedLeaderCard = leaderCards.get(1);
			case "3":  selectedLeaderCard = leaderCards.get(2);
			default: selectedLeaderCard = leaderCards.get(0);
				System.out.println("Invalid choice, try again!");
					 //this.chooseLeaderToTurn(keyboard); Send a Message
			}
		
		}
		else {
			int leaderToPlay = Integer.parseInt(leaderCard);
			if (leaderToPlay < playedOncePerTurnLeaderCards.size()){
				selectedLeaderCard = playedOncePerTurnLeaderCards.get(leaderToPlay-1);
			}
			else {
				System.out.println("Invalid choice, try again!");
				selectedLeaderCard = null;
			} 
		}
		
		LeaderAction leaderTurn = new LeaderAction(remoteView.getPlayer(), selectedLeaderCard,  playOrTurn, remoteView.getGame());
		remoteView.response(leaderTurn);
		
		
		}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		super.inputFromCli(keyboard);
		System.out.println("Do you want to turn a Leader Card or to play one of your activated Leader Card? "
				+ "\n(1) Turn Leader Card \n(2) Play Leader Card");
		String choice = keyboard.nextLine();
		switch (choice){
		case "1":	chooseLeaderToTurn(keyboard);
					break;
		case "2": 	chooseLeaderToPlay(keyboard);
					break;
		default: System.out.println("Invalid choice, try again!");
					inputFromCli(keyboard);
	}
		


	}
	
	private void chooseLeaderToPlay(Scanner keyboard) {
		if (playedOncePerTurnLeaderCards.isEmpty()){
			System.out.println("You didn't play any Leader Card yet or none of your Leader Cards can help you now!");
		}
		else{
			for (int i = 0; i < playedOncePerTurnLeaderCards.size(); i++) {
				System.out.println("Insert " +"(" + i+1 + ") to play " + playedOncePerTurnLeaderCards.get(i).getName());
			}
			leaderCard = keyboard.nextLine();
			
		}
	}

	public void chooseLeaderToTurn(Scanner keyboard){
		System.out.println("Which leader card do you want to activate? /n (1) " + leaderCards.get(0) + "(2) " + leaderCards.get(1) + "(3) " + leaderCards.get(2) );
		leaderCard = keyboard.nextLine();
		
	}
	
}
