package it.polimi.ingsw.GC_21.BOARD;


import java.util.ArrayList;

import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CouncilPalace {
	private MultipleActionSpace multipleActionSpace;
	private Game game;
	
	public CouncilPalace(Game game) {
		multipleActionSpace = new MultipleActionSpace(1, new Effect(new Possession(1, 0, 0, 0, 0, 0, 0), 1, game), game);
		this.game=game;
	}

	public MultipleActionSpace getMultipleActionSpace() {
		return multipleActionSpace;
	}

	public void setMultipleActionSpace(MultipleActionSpace multipleActionSpace) {
		this.multipleActionSpace = multipleActionSpace;
	}
	
	public Player[] getTurnOrder() {
		ArrayList<FamilyMember> familyMembers = getMultipleActionSpace().getFamilyMembers();
		Player[] turnOrder = new Player[4];
		int j = 0;
		for (int i = 0; i < familyMembers.size(); i++) {
			if (familyMembers.get(i) != null) {
				Player player = familyMembers.get(i).getOwnerPlayer();
				for (int k = i; k < familyMembers.size(); k++) {//set to null all family members of the same player taken for the first time (cycle with i)
					if (familyMembers.get(k).equals(player)) {
						familyMembers.set(k, null);
					}
				}
				turnOrder[j] = player;
			}
		}
		return turnOrder;
	}



	
	@Override
	public String toString() {
		return "CouncilPalace:[" + multipleActionSpace.toString() + "]";
	}
	
	

}