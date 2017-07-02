package it.polimi.ingsw.GC_21.CONTROLLER;

import java.util.ArrayList;


import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforePlacement;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.UTILITIES.P2SObserver;

import it.polimi.ingsw.GC_21.VIEW.RemoteView;

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
		if (remoteView.getPlayer().getPlayerColor() == Color.Black) {
			if (!action.checkBlack()) {
				action.place();
				return true;
			}
			return false;
		}
		if (action instanceof PlacementAction){
		int indexOfToCallBeforePlacementArray = action.getPlayerInAction().getMyPersonalBoard().getToCallBeforePlacementEffects().size();
		ArrayList<ToCallBeforePlacement> effectsOnTheGo = action.getPlayerInAction().getMyPersonalBoard().getToCallBeforePlacementEffects();			for (int i = 0; i < indexOfToCallBeforePlacementArray; i++) {
			((Effect) effectsOnTheGo.get(i)).activateEffect(action.getPlayerInAction(), action);
			}
		}
		boolean checkAction = action.checkAction();
		if (checkAction) {
			action.Execute();
			return true;
		}
		return false;
	}
	

	
	@Override
	public void updateTurn() {
	}

	@Override
	public boolean updateMessage(ControllerForm controllerForm) {
		controllerForm.setController(this);
		return controllerForm.executeController();
	}

	

	@Override
	public void updateCurrent(MessageToClient message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBroadcast(MessageToClient message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateClose() {
		controllerManager.getRemoteViews().remove(remoteView);
		
	}

	@Override
	public void updateInit() {
		controllerManager.getGames().remove(modelGame);
		controllerManager.getActiveGames().add(modelGame);
	}

	

	@Override
	public void updateBlack() {
		Player blackPlayer = modelGame.getCurrentAge().getCurrentRound().getBlackPlayer();
		Player playerToSwitch = remoteView.getPlayer();
		modelGame.notifyBlackSwitch(blackPlayer, playerToSwitch);
		remoteView.setPlayer(blackPlayer);
		remoteView.getPlayer().setName(remoteView.getUsername());
		for (int i = 0; i < modelGame.getPlayers().size(); i++) {
			modelGame.getPlayers().get(i).setBlackPoint(0);
		}
	}

	@Override
	public void updateBlackSwitch(Player playerToSwitch) {
		// TODO Auto-generated method stub
		
	}


	
}
