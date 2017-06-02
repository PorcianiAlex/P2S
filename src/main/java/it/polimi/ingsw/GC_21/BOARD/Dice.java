package it.polimi.ingsw.GC_21.BOARD;

import java.util.Random;

import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;

public class Dice {

	private final FamilyMemberColor diceColor;
	private int value;
	private Random random;
	
	private Dice(FamilyMemberColor familyMemberColor) {
		this.diceColor = familyMemberColor;
		random=new Random();
		this.value = random.nextInt(6)+1;
	}
	
	public Dice() {
		this.diceColor = FamilyMemberColor.Neutral;
		this.value = 0;
	}
	
	public static Dice[] factoryDices(){
		Dice[] dices=new Dice[3];
		dices[0]=new Dice(FamilyMemberColor.Black);
		dices[1]=new Dice(FamilyMemberColor.White);
		dices[2]=new Dice(FamilyMemberColor.Orange);
		return dices;
	}
	
	
	
	
	public FamilyMemberColor getdiceColor() {
		return diceColor;
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


	@Override
	public String toString() {
		return "Dice [familyMemberColor=" + diceColor.toString() + ", value=" + value + "]";
	}
	
}