
package it.polimi.ingsw.GC_21.GAMECOMPONENTS;

public enum DevCardType {
Building, Territory, Character, Venture;
	
	public static DevCardType geType(int num) {
		if (num==0) {
			return DevCardType.Territory;
		} else if (num==1) {
			return DevCardType.Character;
		}else if (num==2) {
			return DevCardType.Building;
		}else if (num==3) {
			return DevCardType.Venture;
		}else {
			return null;

		}
	}
	
}
