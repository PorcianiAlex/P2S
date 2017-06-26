package it.polimi.ingsw.GC_21.GAMECOMPONENTS;


public class DevelopmentCard extends Card {
	
	private int age;
	private DevCardType devCardType;

	
	public DevelopmentCard(String name) {
		super(name);
	
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public DevCardType getDevCardType() {
		return devCardType;
	}


	public void setDevCardType(DevCardType devCardType) {
		this.devCardType = devCardType;
	}



	@Override
	public String toString() {
		return "DevC [ " + this.getName() + " ]";
	}
	


}