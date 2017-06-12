package it.polimi.ingsw.GC_21.view; 
import java.awt.Insets;import java.io.FileNotFoundException;import java.io.FileOutputStream;import java.io.IOException;import java.io.ObjectOutputStream;import java.io.PrintStream; import java.net.Socket; import java.util.Scanner; import java.util.ArrayList; import java.util.ResourceBundle.Control;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.Current;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION; 
import org.omg.PortableServer.AdapterActivator; 
import it.polimi.ingsw.GC_21.ACTION.Action; 
import it.polimi.ingsw.GC_21.ACTION.CouncilPlacement; 
import it.polimi.ingsw.GC_21.ACTION.CraftAction; 
import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.ACTION.ExcommAction;
import it.polimi.ingsw.GC_21.ACTION.MarketPlacement; 
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement; 
import it.polimi.ingsw.GC_21.BOARD.Color; 
import it.polimi.ingsw.GC_21.BOARD.CraftType; 
import it.polimi.ingsw.GC_21.CLIENT.RmiClient; 
import it.polimi.ingsw.GC_21.CLIENT.RmiClientInterface; 
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType; 
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.LoginMessage;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember; 
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor; 
import it.polimi.ingsw.GC_21.PLAYER.Player; 
import it.polimi.ingsw.GC_21.UTILITIES.Observable; 
import it.polimi.ingsw.GC_21.UTILITIES.P2SObserver;
import it.polimi.ingsw.GC_21.UTILITIES.CurrentObserver;
import it.polimi.ingsw.GC_21.controller.Controller; 
import it.polimi.ingsw.GC_21.controller.ControllerManager; 
public class RemoteView extends Observable<Action> implements P2SObserver, CurrentObserver, Runnable { 
    private Game game;     private Player player; 
    private AdapterConnection adapterConnection; 
    private String username;    private AdapterView adapterView;    private ObjectOutputStream oos;    
  //in the declaration of remote View we create the controller passing the controller manager
  public RemoteView(AdapterConnection adapter, ControllerManager controllerManager) throws IOException { 
        this.adapterConnection = adapter; 
        Controller controller = new Controller(this, controllerManager);        FileOutputStream out = new FileOutputStream("save.ser");    	oos = new ObjectOutputStream(out);
   } 
@Override 
public void run() { 
	 try {		 String viewType = adapterConnection.in();		 if (viewType.equals("GUI")) {			adapterView = new AdapterGUI();		}		 else if (viewType.equals("CLI")) {			adapterView = new AdapterCLI(adapterConnection);		} 
		this.chooseUsername();
		notifyInit();
	 } 	 catch (IOException | ParseException e) {
		e.printStackTrace();
	} } 
  
  public void chooseGame(ControllerManager controllerManager) {
	  adapterConnection.out("Hi "+ username +", welcome to our Lobby!");
	  adapterView.send("\nPress 'C' to create a game or enter the number of the match you want to join:\n" + controllerManager.getGames().toString());
		    String choice = adapterConnection.in(); 
		    notifyControllerManager(choice);
}
 
public void chooseUsername() throws FileNotFoundException, IOException, ParseException {
	adapterView.send("Hi, do you want to Register (1) or Login (2) ?");
	boolean insert = true;
    String choice = adapterConnection.in(); 
    switch (choice) { 
    case "1": insert = true; 
    break; 
    case "2": insert =false;; 
    break;
    default : chooseUsername();
    break;
    }
	adapterView.send("Enter your username: ");
	username = adapterConnection.in();
	adapterView.send("Enter your password: ");
	String psw = adapterConnection.in();
	LoginMessage loginMessage = new LoginMessage(username, psw, insert);
	notifyMessage(loginMessage);
}public void createPlayer(Game game) {
	this.game = game;	game.attach(this);	adapterConnection.out("music");    Color color = null;     Boolean ok = new Boolean(false);      while(!ok) {     adapterView.send("Choose your color: \n 1: BLUE \n 2: RED \n 3: YELLOW \n 4: GREEN");     String choice = adapterConnection.in();     switch (choice) {     case "1": color=Color.Blue;     break;     case "2": color=Color.Red;     break;     case "3": color=Color.Yellow;     break;     case "4": color=Color.Green;     break;     default: color=Color.Blue;       break;     }       ok = this.checkColor(color);   }       player =  new Player(username, color, game);       } 

public boolean checkColor(Color color) {     for (int i = 0; i < game.getPlayers().size(); i++) {       if(color.equals(game.getPlayers().get(i).getPlayerColor())){         adapterConnection.out("Oh grullo! This color is already in use, choose another one, please!");         return false;       }     }     return true;   }  
  public void input() { 
	game.attachCurrent(this);
    adapterConnection.out("Choose your action: " 
        + "\n 1: tower placement" 
        + "\n 2: craft placement " 
        + "\n 3: market placement " 
        + "\n 4: council placement" 
        ); 
    String choice = adapterConnection.in(); 
    switch (choice) { 
    case "1": TowerPlacementInput towerPlacementInput = new TowerPlacementInput();
    	towerPlacementInput.execute(this); 
    	break; 
    case "2": CraftPlacementInput craftPlacementInput = new CraftPlacementInput();
		craftPlacementInput.execute(this);
		break; 
    case "3": MarketPlacementInput marketPlacementInput = new MarketPlacementInput();
     	marketPlacementInput.execute(this);
     	break; 
    case "4": CouncilPlacementInput councilPlacementInput = new CouncilPlacementInput();
    	councilPlacementInput.execute(this); 
    	break; 
    default: adapterConnection.out("Invalid Input");
    	input(); 
    	break; 
    }     
     
  } 
 
  public void response(Action action) {
	  boolean result = this.notifyObservers(action); 
	    if (result==false){ 
	      adapterConnection.out("Oh bischero! Something went wrong! Try again!"); 
	      this.input(); 
	      return; 
	    } 
	    adapterConnection.out("Everything went fine!"); 
	    return; 
}
  
  public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public AdapterConnection getAdapter() {
		return adapterConnection;
	}

	public void setAdapter(AdapterConnection adapter) {
		this.adapterConnection = adapter;
	}

	public AdapterView getAdapterView() {		return adapterView;	}	public void setAdapterView(AdapterView adapterView) {		this.adapterView = adapterView;	}	@Override 
	public void updateTurn() { 
		adapterConnection.out("\n Your resources: " + player.getMyPersonalBoard().toString() + 			  
					"\n" + game.getBoard().toString()); 
		this.input(); 
	  } 	
	@Override
	public void updateExcomm() {
		adapterConnection.out("Bergoglio wants to know if you have been a great guy recently! \n"
			+ "Be careful: if you disappoint him you will get a permanent malus!!!" + 
			"\nDo you want to be excommunicated? Y - N");
		String choiche = adapterConnection.in();
		switch (choiche) {
		case "Y" : this.notifyObservers(new ExcommAction(player, game, true));
		case "N" :	this.notifyObservers(new ExcommAction(player, game, false));
		default: this.notifyObservers(new ExcommAction(player, game, false));
		}
	}
	
	
	


  @Override 
  public void update(String string) { 
    adapterConnection.out(string); 
  } 
 
   
 
  @Override 
  public boolean update(Object change) { 
    return false; 
  } 

@Override
public void updateControllerManager(String string) {
	// TODO Auto-generated method stub
	
}


@Override
public boolean updateMessage(Message message) {
	return true;	
}


@Override
public void updateInit() {
	// TODO Auto-generated method stub
	
}


@Override
public void updateCurrent(InputFromView inputFromView) {
	inputFromView.execute(this);
}


@Override
public void updateString(String comunication) {
	adapterConnection.out(comunication);
} public void serialize() {	try {		oos.writeObject(game);	} catch (IOException e) {		adapterConnection.out("IO Error");		serialize();	}	}


}