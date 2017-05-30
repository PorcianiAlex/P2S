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
import it.polimi.ingsw.GC_21.ACTION.MarketPlacement;
import it.polimi.ingsw.GC_21.ACTION.TowerPlacement;
import it.polimi.ingsw.GC_21.BOARD.Color;
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
		ok = game.checkName(name);
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
		ok = game.checkColor(color);
	}	
		return new Player(name, color, game, this);
				
	}


	
	public void input() {
		out.println("Choose your action: "
				+ "\n 1: tower placement"
				+ "\n 2: craft placement "
				+ "\n 3: market placement "
				+ "\n 4: council placement");
		out.flush();
		String choice = in.nextLine();
		switch (choice) {
		case "1": this.towerPlacementCreator();
		break;
		case "2": this.craftActionCreator();
		break;
		case "3": this.marketPlacementCreator();
		break;
		case "4": this.councilPlacementCreator();
		break;
		default: this.towerPlacementCreator();
			break;
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
		TowerPlacement towerPlacement = TowerPlacement.factoryTowerPlacement(player, this.chooseFamilyMember(), selectedTower, floor, this.chooseHowManyServants(), game.getBoard());
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
		System.out.println("Which reward do you want? \n [2x Coins (1) - 2x Servants (2) - 3x Military Points + 2x Coins (3) - 2x Privileges (4)");
		Scanner scanner = new Scanner(System.in);
		int servantsToConvert = scanner.nextInt();
		MarketPlacement marketPlacement = MarketPlacement.factoryMarketPlacement(player, this.chooseFamilyMember(), servantsToConvert, this.chooseHowManyServants(), game.getBoard());
		boolean result = this.notifyObservers(marketPlacement);

	}
	
	public FamilyMemberColor chooseFamilyMember(){
		System.out.println("Select Family Member [N-O-W-B]:");
		Scanner scanner = new Scanner(System.in);
		switch (scanner.nextLine()) {
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
			System.out.println("You don't have servant to convert!");
			return 0;
		}
		System.out.println("How many servants do you want to convert?:");
		Scanner scanner = new Scanner(System.in);
		int servantsToConvert = scanner.nextInt();
		if (servantsToConvert > playerServant){
			System.out.println("You don't have enough servant to convert, try again!");
			return this.chooseHowManyServants();
		}
		else {
			System.out.println("You are going to convert" + servantsToConvert + "servants");
			return servantsToConvert;
		}
	}
	
	public void councilPlacementCreator() {
		CouncilPlacement councilPlacement = CouncilPlacement.factoryCouncilPlacement(player, this.chooseFamilyMember(), game.getBoard(), this.chooseHowManyServants());	
		boolean result = this.notifyObservers(councilPlacement);

	}
	
	@Override
	public void update(String string) {
		System.out.println(string);
		
	}

	

	@Override
	public boolean update(Object change) {
		return false;
	}

	@Override
	public boolean update() {
		this.game.getBoard().printBoard();
		this.input();
		return true;
	}

	
}