package it.polimi.ingsw.GC_21.CONTROLLER;

import it.polimi.ingsw.GC_21.CLIENT.CheckLobbyMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.AdapterConnection;
import it.polimi.ingsw.GC_21.VIEW.CreatePlayerInput;
import it.polimi.ingsw.GC_21.VIEW.RemoteView;

public class LobbyController extends ControllerForm {
	private ControllerManager controllerManager;
	private RemoteView remoteView;
	private Game modelGame;
	private boolean create;
	private int joined;
	private boolean reconnectionChoice;
	private boolean savedGamesChoice;

	
	public LobbyController(boolean create, int joined, boolean reconnection, boolean savedGames) {
		this.create = create;
		this.joined = joined;
		this.reconnectionChoice = reconnection;
		this.savedGamesChoice = savedGames;
	}
	
	@Override
	public boolean executeController() {
		controllerManager = controller.getControllerManager();
		remoteView  = controller.getRemoteView();
		MessageToClient checkLobbyMessage;
		if (savedGamesChoice) {
			controllerManager.addRemoteView(remoteView);
			modelGame = controllerManager.getMySavedGame(remoteView.getUsername());
			if (modelGame.getNumberOfPlayersActuallyPresent() == 0) {
				modelGame.clear();
			}
			gameConnection();
			Player player = getMyPlayer();
			int number = modelGame.getNumberOfPlayersActuallyPresent();
			number ++;
			modelGame.setNumberOfPlayersActuallyPresent(number);
			remoteView.setPlayer(player);
			modelGame.attachPlayer(player, remoteView);
			if (modelGame.getNumberOfPlayersActuallyPresent() == modelGame.getNumberOfPlayers()) {
				modelGame.executeGame();
				checkLobbyMessage = new MessageToClient(true, "Connecting to the saved game");
				modelGame.executeGame();
			}
			else {
				checkLobbyMessage =new MessageToClient(false, "Waiting for the other players of the game");
			}
		}
		else if (reconnectionChoice) {
			String username = remoteView.getUsername();
			AdapterConnection myAdapterConnection = remoteView.getAdapter();//take my new adapter connection
			remoteView = controllerManager.getMyActiveRemoteView(username);//reconnecting with the the old remote view
			remoteView.setAdapter(myAdapterConnection);//change adapter because the old one is corrupted
			remoteView.setDisconnected(false);
			checkLobbyMessage = new MessageToClient(true, "Reconnecting in the game");
		}
		else if(create) {
			controllerManager.addRemoteView(remoteView);
	        modelGame = controllerManager.createGame(controller);
	        gameConnection();
	        checkLobbyMessage = new CheckLobbyMessage(true, "Welcome in the new game"); 
	        
	    } 
	    else if (joined <=  controllerManager.getGames().size() && joined > 0) {
			controllerManager.addRemoteView(remoteView);
			modelGame = controllerManager.getGames().get(joined-1); //take the selected game
			gameConnection();
			checkLobbyMessage = new CheckLobbyMessage(true, "Welcome in the selected game");     
		}
	    else {
	         checkLobbyMessage = new CheckLobbyMessage(false, "Your game choice is not valid"); 
		}  
		
		remoteView.getAdapter().sendObject(checkLobbyMessage);
        remoteView.inputObject();
	      
	       
		return true;
	}
	
	public void gameConnection() {
		modelGame.attach(remoteView);
		controller.setModelGame(modelGame);
		remoteView.setGame(modelGame);
	}
	
	public Player getMyPlayer() {
		for (int i = 0; i < modelGame.getPlayers().size(); i++) {
			if (remoteView.getUsername().equals(modelGame.getPlayers().get(i).getName())) {
				return modelGame.getPlayers().get(i);
			}
		}
	
	return null;
	}
	
}
