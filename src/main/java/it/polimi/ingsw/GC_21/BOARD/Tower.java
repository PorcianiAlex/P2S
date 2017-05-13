package it.polimi.ingsw.GC_21.BOARD;

import java.util.*;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.FamilyMemberColor;

public class Tower {

	private DevCardType devCardType;
	private Floor[] floors = new Floor[4];

	public Tower(DevCardType devCardType) {
		this.devCardType = devCardType;
		for (int i = 0; i < floors.length; i++) {
			floors[i]=new Floor(i+1);
		}
	}

	public static Tower[] generateTowers(){
		Tower[] towers=new Tower[4];
		towers[0]=new Tower(DevCardType.Building);
		towers[1]=new Tower(DevCardType.Territory);
		towers[2]=new Tower(DevCardType.Character);
		towers[3]=new Tower(DevCardType.Venture);
		return towers;
	}
	
	public Floor[] getFloors() {
		return floors;
	}


	public void setFloors(Floor[] floors) {
		this.floors = floors;
	}


	public void checkFamilyMember() {
		// TODO - implement Tower.checkFamilyMember
		throw new UnsupportedOperationException();
	}

}