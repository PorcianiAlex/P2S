package it.polimi.ingsw.GC_21.view;

import java.util.ArrayList;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;
import it.polimi.ingsw.GC_21.controller.ControllerManager;

public class LobbyMessage extends Message {
	private ControllerManager controllerManager;
	private RemoteView remoteView;
	private Game modelGame;
	private boolean create;
	private int joined;
	
	public LobbyMessage(boolean create, int joined) {
		super();
		this.create = create;
		this.joined = joined;
	}
	
	@Override
	public boolean convert() {
		controllerManager = controller.getControllerManager();
		remoteView  = controller.getRemoteView();
		controllerManager.addRemoteView(remoteView);
		modelGame = controller.getModelGame();
		if(create) {
	        modelGame = controllerManager.createGame(controller);
	        remoteView.createPlayer(modelGame); 
	        letStart(); 
	    } 
	    else {  
	      modelGame = controllerManager.getControllers().get(joined-1).getModelGame(); //take the selected game
	      remoteView.createPlayer(modelGame); 
	      modelGame.notifyString(remoteView.getPlayer().getName()+" joins the match! \nActual number of player: " + modelGame.getPlayers().size());
	      remoteView.getAdapter().out("Waiting for the 'start' by the game host"); 
	    }    
		return true;
	}
	
	public void letStart() { 
	    remoteView.getAdapterView().send("Write 'start' when you want to start the game! \nYou must be 2 at least"); 
	    String string = remoteView.getAdapter().in(); 
	    if("start".equals(string) /*&& modelGame.getPlayers().size()>1 || modelGame.getPlayers().size()==4*/ ) { 
	      modelGame.executeGame(); 
	    } else { letStart(); } 
	  } 

}
