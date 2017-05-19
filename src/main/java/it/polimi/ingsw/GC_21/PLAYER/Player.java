package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

public class Player {

	ArrayList<FamilyMember> Family;
	private String name;
	private String playerColor;
	private PersonalBoard myPersonalBoard;

	public void chooseAction() {
		// TODO - implement Player.chooseAction
		throw new UnsupportedOperationException();
	}

	public void chooseBonusTile() {
		// TODO - implement Player.chooseBonusTile
		throw new UnsupportedOperationException();
	}

	public ArrayList<FamilyMember> getFamily() {
		return Family;
	}

	public void setFamily(ArrayList<FamilyMember> Family) {
		this.Family = Family;
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
	

}