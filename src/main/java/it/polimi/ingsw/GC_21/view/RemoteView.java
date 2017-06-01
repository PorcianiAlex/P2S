package it.polimi.ingsw.GC_21.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.omg.PortableServer.AdapterActivator;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CouncilPlacement;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.ACTION.MarketPlacement;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClientInterface;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMember;
import it.polimi.ingsw.GC_21.PLAYER.FamilyMemberColor;
import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.UTILITIES.Observable;
import it.polimi.ingsw.GC_21.UTILITIES.P2SObserver;
import it.polimi.ingsw.GC_21.UTILITIES.ModelObserver;
import it.polimi.ingsw.GC_21.controller.Controller;

public class RemoteView extends Observable<Action> implements P2SObserver, Runnable {
  
		private Game game;
		private Player player;
	
	    private Socket socket;
	    private ArrayList<RemoteView> threads;
	    private ConnectionType connectionType;
	    private Adapter adapter;
	

	
	public RemoteView(Socket socket, ArrayList<RemoteView> threads, Game game) throws IOException {
		this.game = game;
		this.socket = socket;
        this.threads = threads;
        this.connectionType = ConnectionType.Socket;
        this.adapter = new SocketAdapter(socket);
        
       }
	
	public RemoteView(ArrayList<RemoteView> threads, Game game, RmiClientInterface rmiClient) {
		this.game = game;
		this.threads = threads;
        this.connectionType = ConnectionType.Rmi;
        this.adapter = new RmiAdapter(rmiClient);
       	}

	@Override
    public void run() {
                   
            player = this.createPlayer();
    		game.attach(this);

      
    }

public Player createPlayer() {
		
		String name = new String();
		Color color = null;
		Boolean ok = new Boolean(false);
		while(!ok) { //if name is already in use, retry!
		adapter.out("Choose your name");
		name = adapter.in();
		ok = this.checkName(name);
		}
		ok = false;
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
		return new Player(name, color, game, this);
				
	}


	
	public void input() {
		adapter.out("Choose your action: "
				+ "\n 1: tower placement"
				+ "\n 2: craft placement "
				+ "\n 3: market placement "
				+ "\n 4: council placement"
				+ "\n 5: craft placement");
		String choice = adapter.in();
		switch (choice) {
		case "1": this.towerPlacementCreator();
		break;
		case "2": this.craftActionCreator();
		break;
		case "3": this.marketPlacementCreator();
		break;
		case "4": this.councilPlacementCreator();
		break;
		case "5": this.craftPlacementCreator();
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
		boolean result = this.notifyObservers(craftPlacement);
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
		case "2": return DevCardType.Character;
		case "3": return DevCardType.Building;
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
		boolean result = this.notifyObservers(towerPlacement);
		if (result==false){
			adapter.out("Oh bischero! Something went wrong! Try again!");
			this.input();
			return;
		}
		adapter.out("Everything went fine!");
		return;
	}
	
	public void craftActionCreator() {
		CraftAction craftAction;
		//controller.setAction(towerPlacement);
	}
	
	public void marketPlacementCreator() {
		adapter.out("Which reward do you want? \n [2x Coins (1) - 2x Servants (2) - 3x Military Points + 2x Coins (3) - 2x Privileges (4)");
		String Areastring = adapter.in();
		int AreaToPlace = Integer.parseInt(Areastring);
		int servantsToConvert = this.chooseHowManyServants();
		FamilyMemberColor selectedFamilyMember = this.chooseFamilyMember();
		MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(player, selectedFamilyMember, AreaToPlace, servantsToConvert, game.getBoard());
		
		boolean result = this.notifyObservers(marketPlacement);

	}
	
	public FamilyMemberColor chooseFamilyMember(){
		adapter.out("Select Family Member [ N - O - W - B]:");
		String choice = adapter.in();
		switch (choice) {
		case "1": return FamilyMemberColor.Neutral;
		case "2": return FamilyMemberColor.Orange;
		case "3": return FamilyMemberColor.White;
		case "4": return FamilyMemberColor.Black;
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
		boolean result = this.notifyObservers(councilPlacement);

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
	public boolean update() {
		adapter.out(game.getBoard().toString());
		this.input();
		return true;
	}

	public boolean checkName(String name) {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if(name.equals(game.getPlayers().get(i).getName())){
				adapter.out("Oh bischero! This name is already in use, choose another one, please!");
				return false;
			}
		}
		return true;
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
	
}