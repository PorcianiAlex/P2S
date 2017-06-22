package it.polimi.ingsw.GC_21.VIEW;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.ACTION.LeaderAction;
import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class LeaderInput extends InputForm{//TODO to correct
	private Player player;
	private ArrayList<LeaderCard> leaderCards;
	private ArrayList<OncePerTurnLeaderCard> playedOncePerTurnLeaderCards;
	private String leaderCard;
	private boolean turningLeaderCard; 
	private LeaderCard selectedLeaderCard;
		

	
	public LeaderInput(Player player) {
		this.player = player;
		this.leaderCards = player.getMyPersonalBoard().getLeaderCards();
		this.playedOncePerTurnLeaderCards = player.getMyPersonalBoard().getPlayedOncePerTurnLeaderCards();
	}

	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		if (turningLeaderCard) { //If I want to turn a leader card
			switch(leaderCard){//Change the selection of the card
			case "1":  selectedLeaderCard = leaderCards.get(0);	
						break;
			case "2":  selectedLeaderCard = leaderCards.get(1);
						break;
			case "3":  selectedLeaderCard = leaderCards.get(2);	
						break;
			default: 
					this.execute(remoteView);
					 //this.chooseLeaderToTurn(keyboard); Send a Message
			}
		
		}
		else {
			try {
				int leaderToPlay = Integer.parseInt(leaderCard);
				if (leaderToPlay == -1) {
					ChooseActionMessage chooseActionMessage = new ChooseActionMessage("You didn't play any Leader Card yet or none of your Leader Cards can help you now!",  remoteView.getPlayer());
					remoteView.getAdapter().sendObject(chooseActionMessage);
					return;
				}
				if (leaderToPlay < playedOncePerTurnLeaderCards.size() && leaderToPlay!=-1){
					selectedLeaderCard = playedOncePerTurnLeaderCards.get(leaderToPlay-1);
				}
				else {
					System.out.println("Invalid choice, try again!");
					selectedLeaderCard = null;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid choice, try again!");
				this.execute(remoteView);
				selectedLeaderCard = null;
			} 
		}
		
		LeaderAction leaderTurn = new LeaderAction(remoteView.getPlayer(), selectedLeaderCard,  turningLeaderCard, remoteView.getGame());
		remoteView.response(leaderTurn);	
		}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		super.inputFromCli(keyboard);
		System.out.println("Do you want to turn a Leader Card or to play one of your activated Leader Card? "
				+ "\n(1) Turn Leader Card \n(2) Play Leader Card");
		String choice = keyboard.next();
		keyboard.reset();
		switch (choice){
		case "1":	chooseLeaderToTurn(keyboard);
					turningLeaderCard = true;
					break;
		case "2": 	chooseLeaderToPlay(keyboard);
					turningLeaderCard = false;
					break;
		default: System.out.println("Oh grullo are you joking?? Turn or Play");
					inputFromCli(keyboard);
	}
		


	}
	
	private void chooseLeaderToPlay(Scanner keyboard) {
		if (playedOncePerTurnLeaderCards.isEmpty()){
			leaderCard = "-1";
		}
		else{
			for (int i = 0; i < playedOncePerTurnLeaderCards.size(); i++) {
				System.out.println("Insert " +"(" + i+1 + ") to play " + playedOncePerTurnLeaderCards.get(i).getName());
			}
			leaderCard = keyboard.next();
		}
	}

	public void chooseLeaderToTurn(Scanner keyboard){
		System.out.println("Which leader card do you want to activate?");
		for (int i = 0; i < leaderCards.size(); i++) {
			int indexOfLeaderCards = i+1;
			System.out.println("Insert " +"(" + indexOfLeaderCards + ") to turn " + leaderCards.get(i).getName());
		}
		leaderCard = keyboard.next();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}

	public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;
	}

	public ArrayList<OncePerTurnLeaderCard> getPlayedOncePerTurnLeaderCards() {
		return playedOncePerTurnLeaderCards;
	}

	public void setPlayedOncePerTurnLeaderCards(ArrayList<OncePerTurnLeaderCard> playedOncePerTurnLeaderCards) {
		this.playedOncePerTurnLeaderCards = playedOncePerTurnLeaderCards;
	}

	public String getLeaderCard() {
		return leaderCard;
	}

	public void setLeaderCard(String leaderCard) {
		this.leaderCard = leaderCard;
	}

	
}
