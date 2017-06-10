package it.polimi.ingsw.GC_21.BOARD;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.EFFECT.CraftInfluencer;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.Immediate;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CraftArea {
	private Game game;
	private final CraftType craftType;
	private final SingleActionSpace singleActionSpace;
	private final MultipleActionSpace multipleActionSpace;
	
	public CraftArea(CraftType craftType, Game game) {
		this.game = game;
		this.craftType = craftType;
		this.singleActionSpace = new SingleActionSpace(1, null, game);
		this.multipleActionSpace = new MultipleActionSpace(1, new CraftInfluencer(craftType, -3, game), game);
	}

	public boolean checkCraftFamilyMemberPlayer(Player player) {//return true if there is a family member with the indicated color in the singleActionSpace or in the multiple
		boolean checkSingle = false;
		if (singleActionSpace.Busy && !singleActionSpace.getFamilyMemberLocated().getFamilyMemberColor().equals(FamilyMemberColor.Neutral)){
				 checkSingle = singleActionSpace.getFamilyMemberLocated().getOwnerPlayer().equals(player);
		}	
		  
		return checkSingle || checkPlayerPresenceOnMultipleSpace(player);	
	}
	
	public boolean checkPlayerPresenceOnMultipleSpace(Player player) {
		int i = 0;
		ArrayList<FamilyMember> familyMembersLocated = multipleActionSpace.getFamilyMembers();
		if (familyMembersLocated != null) {
			while (i < familyMembersLocated.size()) {
			      if (!familyMembersLocated.get(i).getFamilyMemberColor().equals(FamilyMemberColor.Neutral) && familyMembersLocated.get(i).getOwnerPlayer().equals(player)) {
				      return true;
			      }
				i++;
			}
		}
		
		return false;
	}


	public CraftType getCraftType() {
		return craftType;
	}
	
	

	public SingleActionSpace getSingleActionSpace() {
		return singleActionSpace;
	}


	public MultipleActionSpace getMultipleActionSpace() {
		return multipleActionSpace;
	}
	
	public ActionSpace selectActionSpace(int selectedActionSpace) {
		if (selectedActionSpace == 1) {
			return getSingleActionSpace();
		}
		else if (selectedActionSpace == 2) {
			return getMultipleActionSpace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "CraftArea [" + craftType + ", singleActionSpace=" + singleActionSpace.toString()
				+ ", multipleActionSpace=" + multipleActionSpace.toString() + "]";
	}

	
	

}