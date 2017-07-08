package it.polimi.ingsw.GC_21.REMOTEVIEW;

import java.util.Scanner;

import javax.naming.TimeLimitExceededException;
import javax.net.ssl.SSLException;
import javax.print.attribute.standard.Copies;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.Pass;
import it.polimi.ingsw.GC_21.CLIENT.Connections;
import it.polimi.ingsw.GC_21.CLIENT.TimerThread;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class ActionInput extends InputForm  {
	protected Player player;
	


	public InputForm chooseAction(Player player, TimerThread timerThread) throws ExecutionException, InterruptedException {
		System.out.println("Choose your action: " + "\n 1: Tower placement" + "\n 2: Craft placement "
				+ "\n 3: Market placement " + "\n 4: Council placement" + "\n 5: Pass" + "\n 6: Play Leader Card" + "\n 7: Discard Leader Card");
		String choice = takeInput(this);
			switch (choice) {
			case "1":
				TowerPlacementInput towerPlacementInput = new TowerPlacementInput(this, input);
				towerPlacementInput.inputFromCli();
				return towerPlacementInput;
			case "2":
				CraftPlacementInput craftPlacementInput = new CraftPlacementInput(this, input);
				craftPlacementInput.inputFromCli();
				return craftPlacementInput;
			case "3":
				MarketPlacementInput marketPlacementInput = new MarketPlacementInput(this, input);
				marketPlacementInput.inputFromCli();
				return marketPlacementInput;
			case "4":
				CouncilPlacementInput councilPlacementInput = new CouncilPlacementInput(this, input);
				councilPlacementInput.inputFromCli();
				return councilPlacementInput;
			case "5":
				PassInput passInput = new PassInput();
				passInput.inputFromCli();
				return passInput;
			case "6":
				if (player.getPlayerColor() == Color.Black) {
					System.out.println("Invalid input, try again!");
					return this.chooseAction(player, timerThread);
				}
				LeaderInput leaderInput = new LeaderInput(this, input, player);
				leaderInput.inputFromCli();
				return leaderInput;
			case "7":
				if (player.getPlayerColor() == Color.Black) {
					System.out.println("Invalid input, try again!");
					return this.chooseAction(player, timerThread);
				}
				DiscardInput discardInput = new DiscardInput(this, input, player);
				discardInput.inputFromCli();
				return discardInput;
			default:
				System.out.println("Invalid input, try again!");
				return this.chooseAction(player, timerThread);
			} 
	}




			


	}


