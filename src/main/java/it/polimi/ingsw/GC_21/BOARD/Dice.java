package it.polimi.ingsw.GC_21.BOARD;

import java.util.Random;

import javax.print.attribute.standard.PrinterLocation;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.FamilyMemberColor;

public class Dice {

	private FamilyMemberColor familyMemberColor;
	private int value;
	private Random random;
	
	public Dice(FamilyMemberColor familyMemberColor) {
		this.familyMemberColor = familyMemberColor;
		random=new Random();
		this.value = random.nextInt(6)+1;
	}
	
	public FamilyMemberColor getFamilyMemberColor() {
		return familyMemberColor;
	}

	public void setFamilyMemberColor(FamilyMemberColor familyMemberColor) {
		this.familyMemberColor = familyMemberColor;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public static Dice[] generateDices(){
		Dice[] dices=new Dice[3];
		dices[0]=new Dice(FamilyMemberColor.Black);
		dices[1]=new Dice(FamilyMemberColor.White);
		dices[2]=new Dice(FamilyMemberColor.Orange);
		return dices;
	}
	
}