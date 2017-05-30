package it.polimi.ingsw.GC_21.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Privileges;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPalace {
	private MultipleActionSpace multipleActionSpace;
	
	public CouncilPalace() {
		multipleActionSpace = new MultipleActionSpace(1, new Effect(new Possession(1, 0, 0, 0, 0, 0, 0), 1));
	}

	public MultipleActionSpace getMultipleActionSpace() {
		return multipleActionSpace;
	}

	public void setMultipleActionSpace(MultipleActionSpace multipleActionSpace) {
		this.multipleActionSpace = multipleActionSpace;
	}
	
	public Color[] getTurnOrder() {
		ArrayList<FamilyMember> playersColorInCounciList = getMultipleActionSpace().getFamilyMembers();
		Color[] turnOrder = new Color[4];
		int j = 0;
		for (int i = 0; i < playersColorInCounciList.size(); i++) {
			if (playersColorInCounciList.get(i) != null) {
				Color color = playersColorInCounciList.get(i).getColor();
				for (int k = i; k < playersColorInCounciList.size(); k++) {
					if (playersColorInCounciList.get(k).equals(color)) {
						playersColorInCounciList.set(k, null);
					}
				}
				turnOrder[j] = color;
			}
		}
		return turnOrder;
	}



	
	@Override
	public String toString() {
		return "CouncilPalace:[" + multipleActionSpace + "]";
	}
	
	

}