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
import it.polimi.ingsw.GC_21.view.ExcommInput;
import it.polimi.ingsw.GC_21.view.InputForm;
import it.polimi.ingsw.GC_21.view.RemoteView;

public class Controller implements P2SObserver<Action>{


	private Game modelGame;
	private RemoteView remoteView;
	private ControllerManager controllerManager;

	

	public Controller(Game modelGame) {
		this.modelGame = modelGame;
		
	}
	
	public Controller (RemoteView remoteView, ControllerManager controllerManager) {
		this.remoteView = remoteView;
		this.controllerManager = controllerManager;
		remoteView.attach(this);
	}

	public Game getModelGame() {
		return modelGame;
	}


	public void setModelGame(Game modelGame) {
		this.modelGame = modelGame;
	}
	
	

	

	public RemoteView getRemoteView() {
		return remoteView;
	}

	public void setRemoteView(RemoteView remoteView) {
		this.remoteView = remoteView;
	}
	

	public ControllerManager getControllerManager() {
		return controllerManager;
	}

	public void setControllerManager(ControllerManager controllerManager) {
		this.controllerManager = controllerManager;
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
			//System.out.println(action.toString());
			return true;
		}
		return false;
	}

	
	@Override
	public void updateTurn() {
	}

	@Override
	public void updateControllerManager(String choice) {
	}
	
	

	@Override
	public void update(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateMessage(ControllerForm controllerForm) {
		controllerForm.setController(this);
		return controllerForm.executeController();
		
	}

	@Override
	public void updateInit() {
	}

	@Override
	public void updateCurrent(InputForm inputFromView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateExcomm(ExcommInput excommInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateExcomm() {
		// TODO Auto-generated method stub
		
	}


	
}
