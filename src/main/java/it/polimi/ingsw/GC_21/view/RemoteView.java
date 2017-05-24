package it.polimi.ingsw.GC_21.view;

import java.util.Scanner;
import java.util.ResourceBundle.Control;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CouncilPlacement;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.MarketPlacement;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.FamilyMemberColor;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.controller.Controller;

public class RemoteView {
  
	private Player player;
	private Controller controller;
	
	public RemoteView(Player player, Controller controller) {
		this.player = player;
		this.controller = controller;
	}

	public void input() {
		System.out.println("Choose your action!: "
				+ "/n 1: tower placement"
				+ "/n 2: craft placement "
				+ "/n 3: market placement "
				+ "/n 4: council placement");
		Scanner scanner = new Scanner(System.in);
		switch (scanner.nextLine()) {
		case "1": this.towerPlacementCreator();
		case "2": this.craftActionCreator();
		case "3": this.marketPlacementCreator();
		case "4": this.councilPlacementCreator();
			
			break;

		default:
			break;
		}		
		
	}
	
	public void towerPlacementCreator() {
		
		DevCardType selectedTower;
		int floor;
		FamilyMemberColor familyMemberColor;
		
		System.out.println("Select Tower [1-4]:");
		Scanner scanner = new Scanner(System.in);
		switch (scanner.nextLine()) {
		case "1": selectedTower = DevCardType.Territory;
		case "2": selectedTower = DevCardType.Character;
		case "3": selectedTower = DevCardType.Building;
		case "4": selectedTower = DevCardType.Venture;
		break;
		default: selectedTower = DevCardType.Building;
		break;
		}	
		System.out.println("Select Floor [1-4]:");
		switch (scanner.nextLine()) {
		case "1": floor = 1;
		case "2": floor = 2;
		case "3": floor = 3;
		case "4": floor = 4;
		break;
		default: floor = 1;
		break;
		}	
		System.out.println("Select Family Member [N-O-W-B]:");
		switch (scanner.nextLine()) {
		case "N": familyMemberColor = FamilyMemberColor.Neutral;
		case "O": familyMemberColor = FamilyMemberColor.Orange;
		case "W": familyMemberColor = FamilyMemberColor.White;
		case "B": familyMemberColor = FamilyMemberColor.Black;
		break;
		default: familyMemberColor = FamilyMemberColor.Neutral;
		break;
		}	
		System.out.println("How many servants do you want to convert?:");
		int servants = scanner.nextInt();	
		
		TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(player, familyMemberColor, selectedTower, floor, servants, controller.getModelGame().getBoard());
		//controller.setAction(towerPlacement);
	
	}
	
	public void craftActionCreator() {
		
		CraftAction craftAction;
		//controller.setAction(towerPlacement);
	}
	
	public void marketPlacementCreator() {
		
		MarketPlacement marketPlacement;
		//controller.setAction(towerPlacement);
	}
	
	public void councilPlacementCreator() {
		
		CouncilPlacement councilPlacement;
		//controller.setAction(towerPlacement);
	}
}