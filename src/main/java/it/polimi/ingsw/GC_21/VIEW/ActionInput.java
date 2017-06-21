package it.polimi.ingsw.GC_21.VIEW;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ActionInput extends InputForm {
	protected String choice;
	
	
	
	public ActionInput(String choice) {
		this.choice = choice;
	}
	
	public ActionInput() {
	}
	
	public InputForm chooseAction(Scanner keyboard, Player player) {
			System.out.println("Choose your action: " + "\n 1: Tower placement" + "\n 2: Craft placement "
					+ "\n 3: Market placement " + "\n 4: Council placement" + "\n 5: Pass" + "\n 6: Play Leader Card" + "\n 7: Discard Leader Card");
			choice = keyboard.next();			
			switch (choice) {
			case "1":
				TowerPlacementInput towerPlacementInput = new TowerPlacementInput();
				towerPlacementInput.inputFromCli(keyboard);
				return towerPlacementInput;
			case "2":
				CraftPlacementInput craftPlacementInput = new CraftPlacementInput();
				craftPlacementInput.inputFromCli(keyboard);
				return craftPlacementInput;
			case "3":
				MarketPlacementInput marketPlacementInput = new MarketPlacementInput();
				marketPlacementInput.inputFromCli(keyboard);
				return marketPlacementInput;
			case "4":
				CouncilPlacementInput councilPlacementInput = new CouncilPlacementInput();
				councilPlacementInput.inputFromCli(keyboard);
				return councilPlacementInput;
			case "5":
				PassInput passInput = new PassInput();
				passInput.inputFromCli(keyboard);
				return passInput;
			case "6":
				LeaderInput leaderInput = new LeaderInput(player);
				leaderInput.inputFromCli(keyboard);
				return leaderInput;
			case "7":
				DiscardInput discardInput = new DiscardInput(player);
				discardInput.inputFromCli(keyboard);
				return discardInput;
			default:
				System.out.println("Invalid input, try again!");
				return chooseAction(keyboard, player);
			}
	}	
}
