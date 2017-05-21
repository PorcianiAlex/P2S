package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public class Item {
	protected final ResourceType resourceType;
	protected int value;
	
	protected Item(int value, ResourceType resourceType) {
		this.value = value;
		this.resourceType = resourceType;
	}
	
	public static Item factoryItem(int value, ResourceType resourceType){
		if (resourceType.equals(ResourceType.Coins)){
			return new Coins(value);
		}
		if (resourceType.equals(ResourceType.Servants)){
			return new Servants(value);
		}
		if (resourceType.equals(ResourceType.MilitaryPoints)){
			return new MilitaryPoints(value);
		}
		if (resourceType.equals(ResourceType.Woods)){
			return new Woods(value);
		}
		if (resourceType.equals(ResourceType.Stones)){
			return new Stones(value);
		}
		if (resourceType.equals(ResourceType.FaithPoints)){
			return new FaithPoints(value);
		}
		if (resourceType.equals(ResourceType.VictoryPoints)){
			return new VictoryPoints(value);
		}
		else{
			return new Privileges(value);
		}
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

	@Override
	public String toString() {
		return "" + value;
	}
	
	
	
	
	
}