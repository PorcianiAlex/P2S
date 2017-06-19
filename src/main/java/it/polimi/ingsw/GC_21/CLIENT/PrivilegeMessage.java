package it.polimi.ingsw.GC_21.CLIENT;

import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.PrivilegeInput;

public class PrivilegeMessage extends MessageToClient{
		protected int privilegesNumber;
		protected Possession rewards;
		protected ArrayList<Possession> earnedRewards;

		
		
	public PrivilegeMessage(Possession rewards, int privilegesNumber, ArrayList<Possession> earnedRewards) {
		super(true, "You have " + privilegesNumber + " privileges left to convert! ");
		this.rewards = rewards;
		this.privilegesNumber = privilegesNumber;
		this.earnedRewards = earnedRewards;
	}

	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		super.executeCLI(keyboard);
		PrivilegeInput privilegeInput = new PrivilegeInput(rewards, privilegesNumber, earnedRewards);
		privilegeInput.inputFromCli(keyboard);
		return privilegeInput;	
	}

}
