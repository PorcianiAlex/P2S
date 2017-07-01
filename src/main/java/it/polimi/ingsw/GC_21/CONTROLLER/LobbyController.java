package it.polimi.ingsw.GC_21.CONTROLLER;

import it.polimi.ingsw.GC_21.CLIENT.CheckLobbyMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.VIEW.AdapterConnection;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;

public class LobbyController extends ControllerForm {
	private ControllerManager controllerManager;
	private RemoteView remoteView;
	private Game modelGame;
	private boolean create;
	private int joined;
	private boolean reconnectionChoice;
	
	public LobbyController(boolean create, int joined, boolean reconnection) {
		this.create = create;
		this.joined = joined;
		this.reconnectionChoice = reconnection;
	}
	
	@Override
	public boolean executeController() {
		controllerManager = controller.getControllerManager();
		remoteView  = controller.getRemoteView();
		MessageToClient checkLobbyMessage;
		if (reconnectionChoice) {
			String username = remoteView.getUsername();
			AdapterConnection myAdapterConnection = remoteView.getAdapter();
			remoteView = controllerManager.getMyActiveRemoteView(username);//reconnecting with the the old remote view
			remoteView.setAdapter(myAdapterConnection);//change adapter because it is corrupted
			checkLobbyMessage = new MessageToClient(true, "Reconnecting in the game");
		}
		else if(create) {
			controllerManager.addRemoteView(remoteView);
	        modelGame = controllerManager.createGame(controller);
			modelGame.attach(remoteView);
			controller.setModelGame(modelGame);
			remoteView.setGame(modelGame);
	        checkLobbyMessage = new CheckLobbyMessage(true, "Welcome in the game"); 


	    } 
	    else if (joined <=  controllerManager.getGames().size() && joined > 0) {
			controllerManager.addRemoteView(remoteView);
	    	 modelGame = controllerManager.getGames().get(joined-1); //take the selected game
	    	 modelGame.attach(remoteView);
	    	 controller.setModelGame(modelGame);
	    	 remoteView.setGame(modelGame);
		     checkLobbyMessage = new CheckLobbyMessage(true, "Welcome in the game"); 
		     
		}
	    else {
	         checkLobbyMessage = new CheckLobbyMessage(false, "Your game choice is not valid"); 
		}  
		
		remoteView.getAdapter().sendObject(checkLobbyMessage);
        remoteView.inputObject();
	      
	       
		return true;
	}
	
}
