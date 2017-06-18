package it.polimi.ingsw.GC_21.EFFECT;


import java.util.ArrayList;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementInfluencer extends Effect implements ToCallBeforePlacement, Permanent{
	private int actionValueBonus;
	private DevCardType towerEffected;
	private Possession discount;
	private ArrayList<FamilyMemberColor> familyMemberInfluenced;
	
	public PlacementInfluencer(int actionValueBonus, DevCardType towerEffected,
			Possession discount, ArrayList<FamilyMemberColor> familyMemberInfluenced) {
		super();
		this.actionValueBonus = actionValueBonus;
		this.towerEffected = towerEffected;
		this.discount = discount;
		this.familyMemberInfluenced = familyMemberInfluenced;
	}
	
	public PlacementInfluencer(int actionValueBonus, DevCardType towerEffected,
			Possession discount) {
		super();
		this.actionValueBonus = actionValueBonus;
		this.towerEffected = towerEffected;
		this.discount = discount;
	}
	
	
	@Override
	public void activateEffect(Player player, Action action) {
		if (checkIfInfluenced(action)){
			int newActionValue = this.actionValueBonus + ((PlacementAction) action).getActionValue();
			((PlacementAction) action).setActionValue(newActionValue);
			((PlacementAction) action).getDiscount().add(discount);
		}
	}
	
	public boolean checkIfInfluenced(Action action){
		boolean isInstanceOfTowerPlacement = false;
		boolean isRightTower = false;
		boolean isFamilyMemberInfluenced = true;
		if(action instanceof TowerPlacement){
			isInstanceOfTowerPlacement = true;
			TowerPlacement thisAction = (TowerPlacement) action;
			FamilyMemberColor selectedFamilyMember = thisAction.getSelectedFamilyMember().getFamilyMemberColor();
			if (this.familyMemberInfluenced != null && !familyMemberInfluenced.contains(selectedFamilyMember)){
				isFamilyMemberInfluenced = false;
			}
		if (((TowerPlacement) action).getSelectedTower().getDevCardType().equals(this.towerEffected) || towerEffected==null){
			isRightTower = true;
			}
		}
		return isInstanceOfTowerPlacement && isRightTower && isFamilyMemberInfluenced;
		}
	}
	
