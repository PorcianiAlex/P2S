package it.polimi.ingsw.GC_21.view; 
 
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.PrintStream; 
import java.net.Socket; 
import java.util.Scanner; 
import java.util.ArrayList; 
import java.util.ResourceBundle.Control;

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
   
    private Game game; 
    private Player player; 
    private Adapter adapter; 
    private String username;
   
 
  //in the declaration of remote View we create the controller passing the controller manager
  public RemoteView(Adapter adapter, ControllerManager controllerManager) throws IOException { 
        this.adapter = adapter; 
        Controller controller = new Controller(this, controllerManager);      
         } 

 
  @Override 
    public void run() { 
	 try {
		this.chooseUsername();
		notifyInit();
	} catch (IOException | ParseException e) {
		e.printStackTrace();
	} 
    } 
  
  public void chooseGame(ControllerManager controllerManager) {
	  adapter.out("Hi "+ username +", welcome to our Lobby!");
	  adapter.out("\nPress 'C' to create a game or enter the number of the match you want to join:");
	  adapter.out("\n" + controllerManager.getGames().toString() ); 
		    String choice = adapter.in(); 
		    notifyControllerManager(choice);
}
 
public void chooseUsername() throws FileNotFoundException, IOException, ParseException {
	
	adapter.out("Hi, do you want to Register (1) or Login (2) ?");
	boolean insert = true;
    String choice = adapter.in(); 
    switch (choice) { 
    case "1": insert = true; 
    break; 
    case "2": insert =false;; 
    break;
    default : chooseUsername();
    break;
    }
	adapter.out("Enter your username: ");
	username = adapter.in();
	adapter.out("Enter your password: ");
	String psw = adapter.in();
	LoginMessage loginMessage = new LoginMessage(username, psw, insert);
	notifyMessage(loginMessage);
	
}


 
public void createPlayer(Game game) {
	this.game = game;
	game.attach(this);
	adapter.out("music");
    Color color = null; 
    Boolean ok = new Boolean(false);  
    while(!ok) { 
    adapter.out("Choose your color: \n 1: BLUE \n 2: RED \n 3: YELLOW \n 4: GREEN"); 
    String choice = adapter.in(); 
    switch (choice) { 
    case "1": color=Color.Blue; 
    break; 
    case "2": color=Color.Red; 
    break; 
    case "3": color=Color.Yellow; 
    break; 
    case "4": color=Color.Green; 
    break; 
    default: color=Color.Blue; 
      break; 
    }   
    ok = this.checkColor(color); 
  }   
    player =  new Player(username, color, game); 
         
  } 
 
 
   
  public void input() { 
	game.attachCurrent(this);
    adapter.out("Choose your action: " 
        + "\n 1: tower placement" 
        + "\n 2: craft placement " 
        + "\n 3: market placement " 
        + "\n 4: council placement" 
        ); 
    String choice = adapter.in(); 
    switch (choice) { 
    case "1": this.towerPlacementCreator(); 
    break; 
    case "2": this.craftPlacementCreator(); 
    break; 
    case "3": this.marketPlacementCreator(); 
    break; 
    case "4": this.councilPlacementCreator(); 
    break; 
    default: this.towerPlacementCreator(); 
      break; 
    }     
     
  } 
   
  public void craftPlacementCreator() { 
    CraftType craftType = selectCraftType(); 
    adapter.out("Where do you want to place your Family Member? Be careful, my dear bischero: \n if you choose the " 
        + "multiple action space you will get a malus on your craft! \n (1) Single Action Space - (2) Multiple Action Space"); 
    String spaceType = adapter.in(); 
    int servantsToConvert = this.chooseHowManyServants(); 
    FamilyMemberColor selectedFamilyMember = this.chooseFamilyMember(); 
    CraftPlacement craftPlacement = CraftPlacement.factoryCraftPlacement(player, selectedFamilyMember, game.getBoard(), servantsToConvert, craftType, Integer.parseInt(spaceType)); 
    this.response(craftPlacement);
  } 
   
  public CraftType selectCraftType(){ 
    adapter.out("Which kind of craft do you want to execute? (1) Production - (2) Harvest"); 
    String craftType = adapter.in(); 
    switch (craftType){ 
      case "1": return CraftType.Production; 
      case "2": return CraftType.Harvest; 
      default: return CraftType.Production; 
    } 
  } 
  public DevCardType selectTower(){ 
    adapter.out("Select Tower [1-4]:"); 
    String choice = adapter.in(); 
    switch (choice) { 
    case "1": return DevCardType.Territory; 
    case "2": return DevCardType.Building; 
    case "3": return DevCardType.Character; 
    case "4": return DevCardType.Venture; 
    default: return DevCardType.Building; 
    }   
  } 
   
  public int selectFloor(){ 
    adapter.out("Select Floor [1-4]:"); 
    String choicestring = adapter.in(); 
    int choice = Integer.parseInt(choicestring); 
    if (choice <=4 && choice >=1){ 
      return choice; 
    } 
    else { 
      adapter.out("Invalid floor choice, try again!"); 
      return this.selectFloor(); 
    } 
  } 
   
  public void towerPlacementCreator() { 
    DevCardType selectedTower; 
    int floor; 
    FamilyMemberColor familyMemberColor; 
    selectedTower = this.selectTower(); 
    floor = this.selectFloor();   
    familyMemberColor = this.chooseFamilyMember(); 
    int servants = this.chooseHowManyServants(); 
    TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(player, familyMemberColor, selectedTower, floor, servants, game.getBoard()); 
   this.response(towerPlacement);
  } 
   
  public void response(Action action) {
	  boolean result = this.notifyObservers(action); 
	    if (result==false){ 
	      adapter.out("Oh bischero! Something went wrong! Try again!"); 
	      this.input(); 
	      return; 
	    } 
	    adapter.out("Everything went fine!"); 
	    return; 
}
  

  public void marketPlacementCreator() { 
    adapter.out("Which reward do you want? \n [2x Coins (1) - 2x Servants (2) - 3x Military Points + 2x Coins (3) - 2x Privileges (4)"); 
    String Areastring = adapter.in(); 
    int AreaToPlace = Integer.parseInt(Areastring); 
    int servantsToConvert = this.chooseHowManyServants(); 
    FamilyMemberColor selectedFamilyMember = this.chooseFamilyMember(); 
    MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(player, selectedFamilyMember, AreaToPlace, servantsToConvert, game.getBoard()); 
     
    this.response(marketPlacement);
 
  } 
   
  public FamilyMemberColor chooseFamilyMember(){ 
    adapter.out("Select Family Member [ N - O - W - B ]:"); 
    String choice = adapter.in(); 
    switch (choice) { 
    case "N": return FamilyMemberColor.Neutral; 
    case "O": return FamilyMemberColor.Orange; 
    case "W": return FamilyMemberColor.White; 
    case "B": return FamilyMemberColor.Black; 
    default: return FamilyMemberColor.Neutral; 
    } 
  } 
   
  public int chooseHowManyServants(){ 
    int playerServant = player.getMyPersonalBoard().getMyPossession().getServants().getValue(); 
    if (playerServant == 0){ 
      adapter.out("You don't have servant to convert!"); 
      return 0; 
    } 
    adapter.out("How many servants do you want to convert?:"); 
    String servstring = adapter.in(); 
    int servantsToConvert = Integer.parseInt(servstring); 
    if (servantsToConvert > playerServant){ 
      adapter.out("You don't have enough servant to convert, try again!"); 
      return this.chooseHowManyServants(); 
    } 
    else { 
      adapter.out("You are going to convert " + servantsToConvert + " servants"); 
      return servantsToConvert; 
    } 
  } 
   
  public void councilPlacementCreator() { 
    CouncilPlacement councilPlacement = CouncilPlacement.factoryCouncilPlacement(player, this.chooseFamilyMember(), game.getBoard(), this.chooseHowManyServants());   
    this.response(councilPlacement); 
 
  } 
   
  @Override 
  public void update(String string) { 
    adapter.out(string); 
  } 
 
   
 
  @Override 
  public boolean update(Object change) { 
    return false; 
  } 
 
  @Override 
  public void updateTurn() { 
	  adapter.out("\n Your resources: " + player.getMyPersonalBoard().toString() + 			  
				"\n" + game.getBoard().toString()); 
    this.input(); 
  } 
 

public boolean checkColor(Color color) { 
    for (int i = 0; i < game.getPlayers().size(); i++) { 
      if(color.equals(game.getPlayers().get(i).getPlayerColor())){ 
        adapter.out("Oh grullo! This color is already in use, choose another one, please!"); 
        return false; 
      } 
    } 
    return true; 
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

public Adapter getAdapter() {
	return adapter;
}

public void setAdapter(Adapter adapter) {
	this.adapter = adapter;
}




@Override
public void updateExcomm() {
	adapter.out("Bergoglio wants to know if you have been a great guy recently! \n"
			+ "Be careful: if you disappoint him you will get a permanent malus!!!" + 
			"\nDo you want to be excommunicated? Y - N");
	String choiche = adapter.in();
	switch (choiche) {
	case "Y" : this.notifyObservers(new ExcommAction(player, game, true));
	case "N" :	this.notifyObservers(new ExcommAction(player, game, false));
	default: this.notifyObservers(new ExcommAction(player, game, false));
	}
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
	adapter.out(comunication);
} 

}