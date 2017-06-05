package it.polimi.ingsw.GC_21.ACTION;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.ActionSpace;
import it.polimi.ingsw.GC_21.BOARD.Board;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CouncilPalace;
import it.polimi.ingsw.GC_21.BOARD.MultipleActionSpace;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Servants;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPlacement extends PlacementAction {

	private CouncilPlacement(Player playerInAction, int actionValue, FamilyMember selectedFamilyMember,
			MultipleActionSpace selectedActionSpace, Servants servantsToConvert, Board board) {
		super(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace , servantsToConvert, board);
	}
	
	public static CouncilPlacement factoryCouncilPlacement(Player playerInAction, FamilyMemberColor familyMemberColor,
			Board board, int servantsNumber) {
	        CouncilPalace selectedCouncilPalace = board.getCouncilPalace();
	        FamilyMember selectedFamilyMember = playerInAction.getSpecificFamilyMember(familyMemberColor);
	        int actionValue = selectedFamilyMember.getAssociatedDice().getValue();
	        Servants servantsToConvert = new Servants(servantsNumber);
	        MultipleActionSpace selectedActionSpace = selectedCouncilPalace.getMultipleActionSpace();
	        CouncilPlacement councilPlacement = new CouncilPlacement(playerInAction, actionValue, selectedFamilyMember, selectedActionSpace, servantsToConvert, board);
	        return councilPlacement;
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
		FamilyMemberColor selectedFamilyMemberColor = FamilyMemberColor.Orange;
		FamilyMemberColor selectedFamilyMemberColor2 = FamilyMemberColor.Black;
		System.out.println("Insert Servants");
		int servantsNumber = tastiera.nextInt();
		System.out.println("Insert Servants");
		int servantsNumber2 = tastiera.nextInt();
		CouncilPlacement councilPlacement = factoryCouncilPlacement(playerInAction, selectedFamilyMemberColor, board, servantsNumber);
		CouncilPlacement councilPlacement2 = factoryCouncilPlacement(playerInAction2, selectedFamilyMemberColor2, board, servantsNumber2);
		boolean checkAction = councilPlacement.checkAction();
		System.out.println("Before:\n" + councilPlacement.toString());
		System.out.println("Check Action [ " + checkAction + " ]");
		System.out.println(councilPlacement.checkToString());
		if (checkAction) {
			councilPlacement.Execute();
		}
		System.out.println("After:\n" + councilPlacement.toString());
		boolean checkAction2 = councilPlacement2.checkAction();
		System.out.println("Before:\n" + councilPlacement2.toString());
		System.out.println("Check Action [ " + checkAction2 + " ]");
		System.out.println(councilPlacement2.checkToString());
		if (checkAction2) {
			councilPlacement2.Execute();
		}
		System.out.println("After:\n" + councilPlacement2.toString());
		System.out.println(board.toString());

	}
	
	
	
}