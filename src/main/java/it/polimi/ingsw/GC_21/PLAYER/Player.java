package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;

public class Player {

	ArrayList<FamilyMember> family;
	private String name;
	private String playerColor;
	private PersonalBoard myPersonalBoard;
	
	
	public Player(String name, String playerColor) {
		super();
		this.family = new ArrayList<FamilyMember>();
		this.name = name;
		this.playerColor = playerColor;
		this.myPersonalBoard = new PersonalBoard();
	}

	public void chooseAction() {
		// TODO - implement Player.chooseAction
		throw new UnsupportedOperationException();
	}

	public void chooseBonusTile() {
		// TODO - implement Player.chooseBonusTile
		throw new UnsupportedOperationException();
	}

	public ArrayList<FamilyMember> getFamily() {
		return family;
	}

	public void setFamily(ArrayList<FamilyMember> Family) {
		this.family = Family;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}

	public PersonalBoard getMyPersonalBoard() {
		return myPersonalBoard;
	}

	public void setMyPersonalBoard(PersonalBoard myPersonalBoard) {
		this.myPersonalBoard = myPersonalBoard;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", playerColor=" + playerColor + "]";
	}
	
	

}