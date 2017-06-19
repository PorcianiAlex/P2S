package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.VIEW.SetFamilyMemberInput;

public class SetFamilyMemberMessage extends MessageToClient{
	private int newFamilyMemberValue;
	private Player player;
	

	
	public SetFamilyMemberMessage(int newFamilyMemberValue, Player player) {
		super(true, "One of your family member can be upgraded to value "
				+ newFamilyMemberValue);
		this.newFamilyMemberValue = newFamilyMemberValue;
		this.player = player;
	}
	
	@Override
	public InputForm executeCLI(Scanner keyboard) {
		SetFamilyMemberInput setFamilyMemberInput = new SetFamilyMemberInput(newFamilyMemberValue, player);
		setFamilyMemberInput.inputFromCli(keyboard);
		return setFamilyMemberInput;
	}
}
