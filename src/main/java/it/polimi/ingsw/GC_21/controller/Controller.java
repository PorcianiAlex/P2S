package it.polimi.ingsw.GC_21.controller;

import java.util.ArrayList;

import java.util.PrimitiveIterator.OfDouble;

import javax.swing.text.View;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.PlacementAction;
import it.polimi.ingsw.GC_21.EFFECT.Effect;
import it.polimi.ingsw.GC_21.EFFECT.ToCallBeforePlacement;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;
import it.polimi.ingsw.GC_21.UTILITIES.P2SObserver;

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
			System.out.println(action.toString());
			return true;
		}
		return false;
	}

	
	@Override
	public void updateTurn() {
		remoteView.chooseGame(controllerManager);		
	}

	@Override
	public void updateControllerManager(String choice) {
		controllerManager.addRemoteView(remoteView);
		if(choice.equals("C")) { 
	        modelGame = controllerManager.createGame();         
	        remoteView.createPlayer(); 
	        letStart(); 
	    } 
	    else {  
	      modelGame = controllerManager.getControllers().get(Integer.parseInt(choice)-1).getModelGame(); //take the selected game
	       remoteView.createPlayer(); 
	       modelGame.notifyString(remoteView.getPlayer().getName()+" joins the match! \nActual number of player: " + modelGame.getPlayers().size());
	        
	         
	        remoteView.getAdapter().out("Waiting for the 'start' by the game host"); 
	    }    
		
	}
	
	public void letStart() { 
	    remoteView.getAdapter().out("Write 'start' when you want to start the game! \nYou must be 2 at least"); 
	    String string = remoteView.getAdapter().in(); 
	    if(string.equals("start") && modelGame.getPlayers().size()>1 || modelGame.getPlayers().size()==4 ) { 
	      modelGame.executeGame(); 
	    } else { letStart(); } 
	  } 

	@Override
	public void updateExcomm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMessage(Message message) {
		message.setController(this);
		message.convert();
		
	}

	@Override
	public void updateInit() {
		remoteView.chooseGame(controllerManager);
	}


	
}
