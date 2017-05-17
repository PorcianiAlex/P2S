package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class Item {
	protected final ResourceType resourceType;
	protected int value;
	
	public Item(int value, ResourceType resourceType) {
		this.value = value;
		this.resourceType = resourceType;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void addItems(Item item) {
		this.value = item.value + this.value;
	}
	
	public boolean equals(Item item){
		if ((this.value == item.value) && this.resourceType.equals(item.resourceType)){
			return true;
		}
		return false;
	}
	
	public boolean compare (Item item){
		if (this.value >= item.value){
			return true;
		}
		return false;
	}
	
}