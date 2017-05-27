package it.polimi.ingsw.GC_21.PLAYER;

import java.util.*;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Player {

	private FamilyMember[] familyMembers;
	private String name;
	private Color playerColor;
	private PersonalBoard myPersonalBoard;
	private RemoteView myView;
	private Game game;
	
	public RemoteView getMyView() {
		return myView;
	}

	public void setMyView(RemoteView myView) {
		this.myView = myView;
	}

	
	public Player(String name, Color playerColor, Game game) {
		super();
		this.familyMembers = FamilyMember.factoryFamilyMembers(game.getBoard().getDices(), this.playerColor);
		this.name = name;
		this.game = game;
		this.playerColor = playerColor;
		this.myPersonalBoard = new PersonalBoard(this);
	}

	public void chooseAction() {
		// TODO - implement Player.chooseAction
		throw new UnsupportedOperationException();
	}

	public void chooseBonusTile() {
		// TODO - implement Player.chooseBonusTile
		throw new UnsupportedOperationException();
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
	}//if you pass null to the method it returns null as the family member doesn't exist 

	

	public FamilyMember[] getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(FamilyMember[] familyMembers) {
		this.familyMembers = familyMembers;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", playerColor=" + playerColor + "]";
	}
	
	

}