package it.polimi.ingsw.GC_21.view;

import java.util.Scanner;

import it.polimi.ingsw.GC_21.BOARD.Color;
import it.polimi.ingsw.GC_21.CLIENT.CheckColorMessage;
import it.polimi.ingsw.GC_21.CLIENT.CheckLoginMessage;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class CreatePlayerInput extends InputForm {
	private Color color;
	
	

	public CreatePlayerInput(Color color) {
		this.color = color;
	}


	public CreatePlayerInput() {
	}


	@Override
	public void execute(RemoteView remoteView) {
		CheckColorMessage checkColorMessage;
		super.execute(remoteView);
		if (checkColor(color, remoteView.getGame())) {
			Player player = new Player(remoteView.getUsername(), color, remoteView.getGame());
			remoteView.setPlayer(player);
			 if (remoteView.getUsername().equals(remoteView.getGame().getHost())) {
				 checkColorMessage = new CheckColorMessage(true, "Color ok");
				 letStart(remoteView);				
					}
			 else {
				remoteView.getGame().notifyString(remoteView.getPlayer().getName()+" joins the match! \nActual number of player: " + remoteView.getGame().getPlayers().size());
				checkColorMessage = new CheckColorMessage(true, "Waiting for the 'start' by the game host");
			}
		}
		else {
			checkColorMessage = new CheckColorMessage(false, "Oh grullo! This color is already in use, choose another one, please!");
		}		
		remoteView.getAdapter().sendObject(checkColorMessage);
		
			}

		
	
	

	public void letStart(RemoteView remoteView) { 
	    remoteView.getAdapter().out("Write 'start' when you want to start the game! \nYou must be 2 at least"); 
	    String string = remoteView.getAdapter().in(); 
	    if("start".equals(string) && remoteView.getGame().getPlayers().size()>1 || remoteView.getGame().getPlayers().size()==4 ) { 
	      remoteView.getGame().executeGame(); 
	    } else { letStart(remoteView); } 
	  } 

	
	
	@Override
	public void inputFromCli(Scanner keyboard) {
		createPlayer(keyboard);
	}
	
	public void createPlayer(Scanner keyboard) {
			System.out.println("Choose your color: \n 1: BLUE \n 2: RED \n 3: YELLOW \n 4: GREEN");
			String choice = keyboard.nextLine();
			switch (choice) {
			case "1":
				color = Color.Blue;
				break;
			case "2":
				color = Color.Red;
				break;
			case "3":
				color = Color.Yellow;
				break;
			case "4":
				color = Color.Green;
				break;
			default:
				color = Color.Blue;
				break;
			}
		}
		
	

	public boolean checkColor(Color color, Game game) {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if (color.equals(game.getPlayers().get(i).getPlayerColor())) {
				return false;
			}
		}
		return true;
	}

}
