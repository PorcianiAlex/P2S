package it.polimi.ingsw.GC_21.ACTION;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.MarketArea;
import it.polimi.ingsw.GC_21.BOARD.SingleActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class MarketPlacement extends PlacementAction {
 

	private MarketPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,  Servants servantsToConvert, SingleActionSpace selectedActionSpace, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board); 
		
	}
	
	public static MarketPlacement factoryMarketPlacement(Player playerInAction, FamilyMemberColor familyMemberColor, 
			int position,  int servantsNumber, Board board) {
		FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(familyMemberColor);
		Servants servantsToConvert = new Servants(servantsNumber);
		int actionValue = selectedFamilyMember.getAssociatedDice().getValue();
		SingleActionSpace selectedActionSpace = board.getMarketArea().getSingleActionSpace()[position-1];
		MarketPlacement marketPlacement = new MarketPlacement(playerInAction, actionValue, selectedFamilyMember, servantsToConvert, selectedActionSpace, board);
		return marketPlacement;
	}
	
	@Override
	public void Execute() {
		super.Execute();
	}
	
	@Override
	public boolean checkAction() {
		return super.checkAction();
	}
	
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public String checkToString() {
		return super.checkToString();
	}
	
	public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);
		Game game = new Game();	
		Board board = game.getBoard();
		Player playerInAction = new Player("Santa", Color.Blue, game);
		Player playerInAction2 = new Player("Alex", Color.Green, game);
		FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Neutral;
		FamilyMemberColor selectedFamilyMemberColor2 = FamilyMemberColor.Black;
		System.out.println("Insert Position");
		int position= tastiera.nextInt();
		System.out.println("Insert Servants");
		int servantsNumber = tastiera.nextInt();
		System.out.println("Insert Position");
		int position2 = tastiera.nextInt();
		System.out.println("Insert Servants");
		int servantsNumber2 = tastiera.nextInt();
		MarketPlacement marketPlacement = factoryMarketPlacement(playerInAction, selectedFamilyMemberColor, position, servantsNumber, board);
		MarketPlacement marketPlacement2 = factoryMarketPlacement(playerInAction, selectedFamilyMemberColor2, position2, servantsNumber2, board);
		boolean checkAction = marketPlacement.checkAction();
		System.out.println("Before:\n" + marketPlacement.toString());
		System.out.println("Check Action [ " + checkAction + " ]");
		System.out.println(marketPlacement.checkToString());
		if (checkAction) {
			marketPlacement.Execute();
		}
		System.out.println("After:\n" + marketPlacement.toString());
		boolean checkAction2 = marketPlacement2.checkAction();
		System.out.println("Before:\n" + marketPlacement2.toString());
		System.out.println("Check Action [ " + checkAction2 + " ]");
		System.out.println(marketPlacement2.checkToString());
		if (checkAction2) {
			marketPlacement2.Execute();
		}
		System.out.println("After:\n" + marketPlacement2.toString());
		System.out.println(board.toString());

	}
	

}