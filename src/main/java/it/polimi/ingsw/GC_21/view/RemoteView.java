package it.polimi.ingsw.GC_21.view;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.ACTION.CouncilPlacement;
import it.polimi.ingsw.GC_21.ACTION.CraftAction;
import it.polimi.ingsw.GC_21.ACTION.CraftPlacement;
import it.polimi.ingsw.GC_21.ACTION.MarketPlacement;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.BOARD.CraftType;
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
	    private int  maxClientsCount;
	    private PrintStream out;
	    private Scanner in;
	

	
	public RemoteView(Socket socket, ArrayList<RemoteView> threads, PrintStream out, Scanner in, Game game) {
		this.game = game;
				
		this.socket = socket;
        this.threads = threads;
        this.out=out;
        this.in=in;
        maxClientsCount = threads.size();
	}
	
	@Override
    public void run() {
        try {
            in = new Scanner(socket.getInputStream());// Canale in ingresso della socket (out del client)
            out = new PrintStream(socket.getOutputStream()); // Canale in uscita della socket (in del client)
            
            player = this.createPlayer();
    		game.attach(this);

        
        } catch (IOException e) {
            System.err.println(e.getMessage());

        }
    }

public Player createPlayer() {
		
		String name = new String();
		Color color = null;
		Boolean ok = new Boolean(false);
		while(!ok) { //if name is already in use, retry!
		out.println("Choose your name");
		out.flush();
		name = in.nextLine();
		ok = this.checkName(name);
		}
		ok = false;
		while(!ok) {
		out.println("Choose your color: \n 1: BLUE \n 2: RED \n 3: YELLOW \n 4: GREEN");
		out.flush();
		switch (in.nextLine()) {
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
		out.println("Choose your action: "
				+ "\n 1: tower placement"
				+ "\n 2: craft placement "
				+ "\n 3: market placement "
				+ "\n 4: council placement"
				+ "\n 5: craft placement");
		out.flush();
		String choice = in.next();
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
		out.println("Where do you want to place your Family Member? Be careful, my dear bischero: \n if you choose the "
				+ "multiple action space you will get a malus on your craft! \n (1) Single Action Space - (2) Multiple Action Space");
		int spaceType = in.nextInt();
		int servantsToConvert = this.chooseHowManyServants();
		FamilyMemberColor selectedFamilyMember = this.chooseFamilyMember();
		CraftPlacement craftPlacement = CraftPlacement.factoryCraftPlacement(player, selectedFamilyMember, game.getBoard(), servantsToConvert, craftType, spaceType);
		boolean result = this.notifyObservers(craftPlacement);
	}
	
	public CraftType selectCraftType(){
		out.println("Which kind of craft do you want to execute? (1) Production - (2) Harvest");
		int craftType = in.nextInt();
		switch (craftType){
			case 1: return CraftType.Production;
			case 2: return CraftType.Harvest;
			default: return CraftType.Production;
		}
	}
	public DevCardType selectTower(){
		out.println("Select Tower [1-4]:");
		out.flush();
		int choice = in.nextInt();
		switch (choice) {
		case 1: return DevCardType.Territory;
		case 2: return DevCardType.Character;
		case 3: return DevCardType.Building;
		case 4: return DevCardType.Venture;
		default: return DevCardType.Building;
		}	
	}
	
	public int selectFloor(){
		out.println("Select Floor [1-4]:");
		out.flush();
		int choice = in.nextInt();
		if (choice <=4 && choice >=1){
			return choice;
		}
		else {
			out.println("Invalid floor choice, try again!");
			out.flush();
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
			out.println("Oh bischero! Something went wrong! Try again!");
			out.flush();
			this.input();
			return;
		}
		out.println("Everything went fine!");
		return;
	}
	
	public void craftActionCreator() {
		CraftAction craftAction;
		//controller.setAction(towerPlacement);
	}
	
	public void marketPlacementCreator() {
		out.println("Which reward do you want? \n [2x Coins (1) - 2x Servants (2) - 3x Military Points + 2x Coins (3) - 2x Privileges (4)");
		int AreaToPlace = in.nextInt();
		int servantsToConvert = this.chooseHowManyServants();
		FamilyMemberColor selectedFamilyMember = this.chooseFamilyMember();
		MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(player, selectedFamilyMember, AreaToPlace, servantsToConvert, game.getBoard());
		out.flush();
		int servantsToConvert = in.nextInt();
		MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(player, this.chooseFamilyMember(), servantsToConvert, this.chooseHowManyServants(), game.getBoard());
		boolean result = this.notifyObservers(marketPlacement);

	}
	
	public FamilyMemberColor chooseFamilyMember(){
		out.println("Select Family Member [ N - O - W - B]:");
		out.flush();
		String choice = in.next();
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
			out.println("You don't have servant to convert!");
			out.flush();
			return 0;
		}
		out.println("How many servants do you want to convert?:");
		out.flush();
		int servantsToConvert = in.nextInt();
		if (servantsToConvert > playerServant){
			out.println("You don't have enough servant to convert, try again!");
			out.flush();
			return this.chooseHowManyServants();
		}
		else {
			out.println("You are going to convert " + servantsToConvert + " servants");
			out.flush();
			return servantsToConvert;
		}
	}
	
	public void councilPlacementCreator() {
		CouncilPlacement councilPlacement = CouncilPlacement.factoryCouncilPlacement(player, this.chooseFamilyMember(), game.getBoard(), this.chooseHowManyServants());	
		boolean result = this.notifyObservers(councilPlacement);

	}
	
	@Override
	public void update(String string) {
		out.println(string);
	}

	

	@Override
	public boolean update(Object change) {
		return false;
	}

	@Override
	public boolean update() {
		out.println(game.getBoard().toString());
		this.input();
		return true;
	}

	public boolean checkName(String name) {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if(name.equals(game.getPlayers().get(i).getName())){
				out.println("Oh bischero! This name is already in use, choose another one, please!");
				out.flush();
				return false;
			}
		}
		return true;
	}
	
	public boolean checkColor(Color color) {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if(color.equals(game.getPlayers().get(i).getPlayerColor())){
				out.println("Oh grullo! This color is already in use, choose another one, please!");
				out.flush();
				return false;
			}
		}
		return true;
	}
	
}