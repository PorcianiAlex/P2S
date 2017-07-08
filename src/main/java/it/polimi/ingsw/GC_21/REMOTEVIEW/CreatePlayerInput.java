package it.polimi.ingsw.GC_21.REMOTEVIEW;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import it.polimi.ingsw.GC_21.CLIENT.CheckColorMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.CLIENT.TimerThread;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Color;
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
		MessageToClient checkColorMessage;
		super.execute(remoteView);
		if (checkColor(remoteView.getGame())) {
			Player player = new Player(remoteView.getUsername(), color, remoteView.getGame());
			remoteView.setPlayer(player);
			remoteView.getGame().attachPlayer(player, remoteView);
			 if (remoteView.getUsername().equals(remoteView.getGame().getHost())) {//if the remote vie is the host
				 remoteView.getGame().attachCurrent(remoteView);
		         checkColorMessage = new CheckColorMessage(true, "Waiting for the players...", true);
		         remoteView.getAdapter().sendObject(checkColorMessage);
					}
			 else {
		         checkColorMessage = new CheckColorMessage(true, "Waiting for the 'start' by the game host", false);
				 remoteView.getAdapter().sendObject(checkColorMessage);
				 MessageToClient joinMessage = new MessageToClient(true, remoteView.getPlayer().getName()+" joins the match! \nActual number of player: " + remoteView.getGame().getPlayers().size()); 
		         remoteView.getGame().notifyBroadcast(joinMessage); 		         
			}
			 checkStartGame(remoteView);		
		}
		else {
			checkColorMessage = new CheckColorMessage(false, "Oh grullo! This color is already in use, choose another one, please!", false);
			remoteView.getAdapter().sendObject(checkColorMessage);
			remoteView.inputObject();
		}					
	}
	
	
	private void checkStartGame(RemoteView remoteView) {
		  Game game = remoteView.getGame();
		  if(game.getPlayers().size() >= 2) {
			  CheckColorMessage checkColorMessage = new CheckColorMessage(true, true, "Write 'start' when you want to start the game! \nYou must be 2 at least", true);
			  remoteView.getGame().notifyCurrent(checkColorMessage);//notify the host that he can start the game until the timer permits it
		   }  
	}

	@Override
	public void inputFromCli() throws InterruptedException {
			System.out.println("Choose your color: \n 1: BLUE \n 2: RED \n 3: YELLOW \n 4: GREEN \n 5: BLACK (The conspirator)");
			String choice = takeInput(this);
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
			case "5":
				color = Color.Black;
				break;
			default:
				System.out.println("Invalid color input, try again!");
				inputFromCli();
				break;
			}
		}
		
	

	public boolean checkColor(Game game) {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			if (color.equals(game.getPlayers().get(i).getPlayerColor())) {
				return false;
			}
		}
		return true;
	}

}
