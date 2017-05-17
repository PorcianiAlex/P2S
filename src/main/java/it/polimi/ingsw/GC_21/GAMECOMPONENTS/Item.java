package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class Item {
	protected int value;

	
	public Item(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean compare (Item item){
		if (this.value >= item.value){
			return true;
		}
		return false;
	}
	
}