package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

import javax.swing.text.View;

import org.omg.CORBA.PRIVATE_MEMBER;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Player {

	private FamilyMember[] familyMembers;
	private String name;
	private Color playerColor;
	private PersonalBoard myPersonalBoard;
	private Game game;
	private boolean checkOnMP;
	private boolean overchargeOnBusyTower;
	private ArrayList<LeaderCard> leaderCards;
	private ArrayList<OncePerTurnLeaderCard> playedOncePerTurnLeaderCards;

	public Player(String name, Color playerColor, Game game) {
		this.familyMembers = FamilyMember.factoryFamilyMembers(game.getBoard().getDices(), this);
		this.name = name;
		this.game = game;
		this.game.addPlayers(this);
		this.playerColor = playerColor;
		this.myPersonalBoard = new PersonalBoard(this);
		this.myPersonalBoard.getMyPossession().add(new Possession(5, 5, 5, 5, 5, 5, 5));
		this.familyMembers = FamilyMember.factoryFamilyMembers(game.getBoard().getDices(), this);
		this.checkOnMP = false;
		this.overchargeOnBusyTower = false;
		this.leaderCards = new ArrayList<LeaderCard>();
		this.playedOncePerTurnLeaderCards = new ArrayList<OncePerTurnLeaderCard>();
		this.pickLeaderCards(game.getLeaderDeck());
	}
	
	public void pickLeaderCards(LeaderDeck leaderDeck) {
		this.leaderCards.add((LeaderCard) leaderDeck.getSingleCard());
		this.leaderCards.add((LeaderCard) leaderDeck.getSingleCard());
		this.leaderCards.add((LeaderCard) leaderDeck.getSingleCard());
	}
	
	
	public void refreshPlayer() {
		for (int i = 0; i < familyMembers.length; i++) {
			familyMembers[i].setPlaced(false);
		}
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
	public boolean isCheckOnMP() {
		return checkOnMP;
	}


	public void setCheckOnMP(boolean checkOnMP) {
		this.checkOnMP = checkOnMP;
	}


	public boolean isOverchargeOnBusyTower() {
		return overchargeOnBusyTower;
	}


	public void setOverchargeOnBusyTower(boolean overchargeOnBusyTower) {
		this.overchargeOnBusyTower = overchargeOnBusyTower;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

	public PersonalBoard getMyPersonalBoard() {
		return myPersonalBoard;
	}

	public void setMyPersonalBoard(PersonalBoard myPersonalBoard) {
		this.myPersonalBoard = myPersonalBoard;
	}
	

	
	
	public FamilyMember getSpecificFamilyMember(FamilyMemberColor familyMemberColor) {
		for (int i = 0; i < familyMembers.length; i++) {
			if (familyMembers[i].getFamilyMemberColor().equals(familyMemberColor)) {
				return familyMembers[i];
			}
		}
		return null;
	}

	

	public FamilyMember[] getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(FamilyMember[] familyMembers) {
		this.familyMembers = familyMembers;
	}

	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "Player [Name=" + name + ", playerColor=" + playerColor + "]";
	}


}