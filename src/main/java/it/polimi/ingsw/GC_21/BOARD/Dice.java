package it.polimi.ingsw.GC_21.BOARD;

import java.util.Random;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class Dice {

	private FamilyMemberColor familyMemberColor;
	private int value;
	private Random random;
	
	private Dice(FamilyMemberColor familyMemberColor) {
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

	public static Dice[] factoryDices(){
		Dice[] dices=new Dice[3];
		dices[0]=new Dice(FamilyMemberColor.Black);
		dices[1]=new Dice(FamilyMemberColor.White);
		dices[2]=new Dice(FamilyMemberColor.Orange);
		return dices;
	}

	@Override
	public String toString() {
		return "Dice [familyMemberColor=" + familyMemberColor.toString() + ", value=" + value + "]";
	}
	
}