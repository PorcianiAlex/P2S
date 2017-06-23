package it.polimi.ingsw.GC_21.VIEW;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.CLIENT.PrivilegeMessage;
import it.polimi.ingsw.GC_21.CONTROLLER.PrivilegeController;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.CONTROLLER.PrivilegeController;

public class PrivilegeInput extends InputForm {
	protected int privilegesNumber;
	protected Possession rewards;
	protected ArrayList<Possession> earnedRewards;
	protected String choice;


	
	public PrivilegeInput(Possession rewards, int privilegesNumber, ArrayList<Possession> earnedRewards) {
		this.rewards = rewards;
		this.privilegesNumber = privilegesNumber;
		this.earnedRewards = earnedRewards;
	}

	@Override
	public void execute(RemoteView remoteView){
		super.execute(remoteView);
		PrivilegeController privilegeController = new PrivilegeController(choice, rewards, privilegesNumber, earnedRewards);
		if (!remoteView.notifyController(privilegeController)){
			PrivilegeMessage privilegeMessage = new PrivilegeMessage(rewards, privilegesNumber, earnedRewards);
			remoteView.getAdapter().sendObject(privilegeMessage);
			}
		}
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		System.out.println("\nChoose your reward! Type: \n (1) 1x Woods 1 x Stones \n (2) 2x Servants"
				+ " \n (3) 2x Coins \n (4) 2x Military Points \n (5) 1x Faith Points");
			this.choice = keyboard.next();
			keyboard.reset();
		}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
	
	}

 
