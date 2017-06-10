package it.polimi.ingsw.GC_21.EFFECT;


import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class PlacementInfluencer extends Effect implements ToCallBeforePlacement{
	private int actionValueBonus;
	private DevCardType towerEffected;
	private Possession discount;
	
	public PlacementInfluencer(int actionValueBonus, DevCardType towerEffected,
			Possession discount) {
		super();
		this.actionValueBonus = actionValueBonus;
		this.towerEffected = towerEffected;
		this.discount = discount;
	}
	
	@Override
	public void activateEffect(Player player, Action action) {
		if (action instanceof TowerPlacement && ((TowerPlacement) action).getSelectedTower().getDevCardType().equals(this.towerEffected) || towerEffected==null){
			int newActionValue = this.actionValueBonus + ((PlacementAction) action).getActionValue();
			((PlacementAction) action).setActionValue(newActionValue);
			((PlacementAction) action).getDiscount().add(discount);
		}
	}
	
}