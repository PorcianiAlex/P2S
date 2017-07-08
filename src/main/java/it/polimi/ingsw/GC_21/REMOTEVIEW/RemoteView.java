package it.polimi.ingsw.GC_21.REMOTEVIEW;import java.io.IOException;import java.util.ArrayList;import it.polimi.ingsw.GC_21.ACTION.Action;import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;import it.polimi.ingsw.GC_21.CLIENT.ChooseActionMessage;import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;import it.polimi.ingsw.GC_21.CLIENT.StartMessage;import it.polimi.ingsw.GC_21.CLIENT.TurnMessage;import it.polimi.ingsw.GC_21.CONTROLLER.Controller;import it.polimi.ingsw.GC_21.CONTROLLER.ControllerForm;import it.polimi.ingsw.GC_21.CONTROLLER.ControllerManager;import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Age;import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Round;import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Turn;import it.polimi.ingsw.GC_21.PLAYER.Player;import it.polimi.ingsw.GC_21.UTILITIES.Observable;import it.polimi.ingsw.GC_21.UTILITIES.P2SObserver;import it.polimi.ingsw.GC_21.UTILITIES.CurrentObserver;public class RemoteView extends Observable<Action> implements P2SObserver, CurrentObserver, Runnable {	private Game game;	private Player player;	private AdapterConnection adapterConnection;	private String username;	private boolean disconnected;	public boolean isDisconnected() {		return disconnected;	}	public void setDisconnected(boolean disconnected) {		this.disconnected = disconnected;	}	// in the declaration of remote View we create the controller passing the	// controller manager	public RemoteView(AdapterConnection adapter, ControllerManager controllerManager) throws IOException {		this.adapterConnection = adapter;		Controller controller = new Controller(this, controllerManager);	}	@Override	public void run() {		StartMessage startMessage = new StartMessage();		adapterConnection.sendObject(startMessage);		inputObject();	}	public void inputObject() {		InputForm inputFromView = adapterConnection.receiveObject();		inputFromView.execute(this);	}	public void response(Action action) {//In controller		boolean result = this.notifyObservers(action);		if (result == false) {			ChooseActionMessage retryActionMessage = new ChooseActionMessage(false, "Oh bischero! Something went wrong! Try again!",  player);			adapterConnection.sendObject(retryActionMessage);			inputObject();			return;		}		return;	}	public String getUsername() {		return username;	}	public void setUsername(String username) {		this.username = username;	}	public Game getGame() {		return game;	}	public void setGame(Game game) {		this.game = game;	}	public Player getPlayer() {		return player;	}	public void setPlayer(Player player) {		this.player = player;	}	public AdapterConnection getAdapter() {		return adapterConnection;	}	public void setAdapter(AdapterConnection adapter) {		this.adapterConnection = adapter;	}	@Override	public void updateTurn() {		game.attachCurrent(this);		Age currentAge = game.getCurrentAge();		Round currentRound = currentAge.getCurrentRound();		Turn currentTurn = currentRound.getCurrentTurn();		String description = "Age: "+ currentAge.getAgeNumber() + " Round: " + currentRound.getRoundNumber() 				+" Turn: "+ currentTurn.getTurnNumber() +" Turn of player : "+ player.getName() +" color: " + player.getPlayerColor().toString();		TurnMessage turnMessage = new TurnMessage(game.getBoard(), game.getPlayers(), 				currentAge.getAgeNumber(), currentRound.getRoundNumber(), 				currentTurn.getTurnNumber(), description);		game.notifyBroadcast(turnMessage);		ChooseActionMessage chooseAction = new ChooseActionMessage(true, "It's your turn!", player);		adapterConnection.sendObject(chooseAction);		inputObject();		if (player.getBlackPoint() >= 2) {//switch player if the black one guessed twice the position of the same player		notifyBlack();		}	}			@Override	public boolean update(Object change) {		return true;	}		@Override	public boolean updateMessage(ControllerForm message) {		return true;	}	@Override	public void updateCurrent(MessageToClient messageToClient) {		adapterConnection.sendObject(messageToClient);		if (messageToClient.isWaiting()){			this.inputObject();		}	}	@Override	public void updateBroadcast(MessageToClient message) {		adapterConnection.sendObject(message);		if (message.isWaiting()){			this.inputObject();		}	}	@Override	public void updateClose() {		try {			adapterConnection.close();			notifyClose();		} catch (IOException e) {			e.printStackTrace();		}			}	@Override	public void updateInit() {		// TODO Auto-generated method stub			}	@Override	public void updateBlack() {		// TODO Auto-generated method stub			}	@Override	public void updateBlackSwitch(Player playerToSwitch) {		setPlayer(playerToSwitch);		player.setName(username);			}	public AdapterConnection getAdapterConnection() {		return adapterConnection;	}	public void setAdapterConnection(AdapterConnection adapterConnection) {		this.adapterConnection = adapterConnection;	}	}