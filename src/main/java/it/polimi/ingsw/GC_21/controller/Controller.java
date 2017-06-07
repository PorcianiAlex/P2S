package it.polimi.ingsw.GC_21.controller;

import java.util.ArrayList;

import java.util.PrimitiveIterator.OfDouble;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforePlacement;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.UTILITIES.P2SObserver;

import it.polimi.ingsw.GC_21.view.RemoteView;

public class Controller implements P2SObserver<Action>{


	private Game modelGame;

	

	public Controller(Game modelGame) {
		this.modelGame = modelGame;
		
	}

	public Game getModelGame() {
		return modelGame;
	}


	public void setModelGame(Game modelGame) {
		this.modelGame = modelGame;
	}
	
	


	@Override
	public boolean update(Action action) {
		if (action instanceof PlacementAction){
			int indexOfToCallBeforePlacementArray = action.getPlayerInAction().getMyPersonalBoard().getToCallBeforePlacementEffects().size();
			ArrayList<ToCallBeforePlacement> effectsOnTheGo = action.getPlayerInAction().getMyPersonalBoard().getToCallBeforePlacementEffects();
			for (int i = 0; i < indexOfToCallBeforePlacementArray; i++) {
				((Effect) effectsOnTheGo.get(i)).activateEffect(action.getPlayerInAction(), action);
			}
		}
		boolean checkAction = action.checkAction();
		if (checkAction) {
			action.Execute();
			System.out.println(action.toString());
			return true;
		}
		return false;
	}

	
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateExcomm() {
		// TODO Auto-generated method stub
		
	}


	
}
