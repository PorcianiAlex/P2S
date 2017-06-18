package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

public class ActionInput extends InputForm {
	protected String choice;
	
	
	
	public ActionInput(String choice) {
		this.choice = choice;
	}
	
	public ActionInput() {
	}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
			System.out.println("Choose your action: " + "\n 1: Tower placement" + "\n 2: Craft placement "
					+ "\n 3: Market placement " + "\n 4: Council placement" + "\n 5: Pass" + "\n 6 Play Leader Card" + "\n 7 Discard Leader Card");
			choice = keyboard.nextLine();			
			switch (choice) {
			case "1":
				TowerPlacementInput towerPlacementInput = new TowerPlacementInput();
				towerPlacementInput.inputFromCli(keyboard);
				break;
			case "2":
				CraftPlacementInput craftPlacementInput = new CraftPlacementInput();
				craftPlacementInput.inputFromCli(keyboard);
				break;
			case "3":
				MarketPlacementInput marketPlacementInput = new MarketPlacementInput();
				marketPlacementInput.inputFromCli(keyboard);
				break;
			case "4":
				CouncilPlacementInput councilPlacementInput = new CouncilPlacementInput();
				councilPlacementInput.inputFromCli(keyboard);
				break;
			case "5":
				PassInput passInput = new PassInput();
				passInput.inputFromCli(keyboard);
				break;
			case "6":
				LeaderInput leaderInput = new LeaderInput();
				leaderInput.inputFromCli(keyboard);
				this.inputFromCli(keyboard);
			default:
				System.out.println("Invalid input, try again!");
				this.inputFromCli(keyboard);
				break;
			}
	}	
}
