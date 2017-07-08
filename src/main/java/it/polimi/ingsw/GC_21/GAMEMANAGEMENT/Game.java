package it.polimi.ingsw.GC_21.GAMEMANAGEMENT;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.BOARD.Board;

import it.polimi.ingsw.GC_21.CLIENT.GameOverMessage;

import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderDeck;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.MilitaryPoints;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.VictoryPoints;
import it.polimi.ingsw.GC_21.PLAYER.Color;
import it.polimi.ingsw.GC_21.PLAYER.Player;

import it.polimi.ingsw.GC_21.UTILITIES.Observable;

public class Game extends Observable implements Serializable{
	
	private static int currentNumberOfGame = 0; 
	private int id;
	private String host;
	private int numberOfPlayers;
	private Board board;
	private ArrayList<Player> players;
	private Age currentAge;
	private ExcommHandler excommHandler;
	private LeaderDeck leaderDeck;
	private ArrayList<Player> victoryPointsRanking;
	private ArrayList<Player> militaryPointsRanking;
	
	
	public Game(String host) {
		this.id = currentNumberOfGame + 1;
		currentNumberOfGame++;
		try {
			this.board = new Board(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.players = new ArrayList<Player>();
		this.excommHandler = new ExcommHandler(this);
		this.host = host;
		this.leaderDeck = new LeaderDeck(this);
		victoryPointsRanking = new ArrayList<Player>();
		militaryPointsRanking = new ArrayList<Player>();
		}
	
	


	public ArrayList<Player> getVictoryPointsRanking() {
		return victoryPointsRanking;
	}




	public void setVictoryPointsRanking(ArrayList<Player> victoryPointsRanking) {
		this.victoryPointsRanking = victoryPointsRanking;
	}

	public ArrayList<Player> getMilitaryPointsRanking() {
		return militaryPointsRanking;
	}




	public void setMilitaryPointsRanking(ArrayList<Player> militaryPointsRanking) {
		this.militaryPointsRanking = militaryPointsRanking;
	}




	public LeaderDeck getLeaderDeck() {
		return leaderDeck;
	}


	public void setLeaderDeck(LeaderDeck leaderDeck) {
		this.leaderDeck = leaderDeck;
	}


	public Game(int id, String host, int numberOfPlayers, Board board, ArrayList<Player> players, Age currentAge,
			ExcommHandler excommHandler, LeaderDeck leaderDeck) {
		super();
		this.id = id;
		this.host = host;
		this.numberOfPlayers = numberOfPlayers;
		this.board = board;
		this.players = players;
		this.currentAge = currentAge;
		this.excommHandler = excommHandler;
		this.leaderDeck = new LeaderDeck(this);
		victoryPointsRanking = new ArrayList<Player>();
		militaryPointsRanking = new ArrayList<Player>();
	}


	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public ExcommHandler getExcommHandler() {
		return excommHandler;
	}

	public void setExcommHandler(ExcommHandler excommHandler) {
		this.excommHandler = excommHandler;
	}

	
	
	public void executeGame() {
		this.assignResources();
		for(int i = 0; i < players.size(); i++){
			victoryPointsRanking.add(players.get(i));
			militaryPointsRanking.add(players.get(i));
		}
		for (int i = 1; i < 4; i++) {
			currentAge = new Age(i, this);
			currentAge.executeAge();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).getMyPersonalBoard().finalEarning();
		}
		GameOverMessage gameOverMessage = new GameOverMessage(true, "And the winner is... " + victoryPointsRanking.get(0).getName() + "!!!\n Congrats!", board, players, victoryPointsRanking);
		this.notifyBroadcast(gameOverMessage);
		this.saveStatistics();

	}


	private void saveStatistics() {
		JSONParser parser = new JSONParser();
		Object obj = new Object();
			try {
				obj = parser.parse(new FileReader("Users.json"));
			} catch (IOException | ParseException e1) {
				e1.printStackTrace();
			}
		    JSONObject users = (JSONObject) obj;
		    JSONArray usersarray= (JSONArray) users.get("users");
		for (int i = 0; i < players.size(); i++) {
		    int victoryPointEarned = 0;
	    	for (Object o : usersarray) {
	    		JSONObject jsonLineItem = (JSONObject) o;
	    		if(players.get(i).getName().equals(jsonLineItem.get("name").toString())){
	    			victoryPointEarned = Integer.parseInt(jsonLineItem.get("VictoryPoints").toString());
	    			int toSaveVictoryPoint = players.get(i).getMyPersonalBoard().getMyPossession().getVictoryPoints().getValue() + victoryPointEarned;
	    			jsonLineItem.put("VictoryPoints", toSaveVictoryPoint);
	    			} 
	    		if(this.getVictoryPointsRanking().get(0).getName().equals(jsonLineItem.get("name").toString())){
	    			int numberOfWins = Integer.parseInt(jsonLineItem.get("numberOfWins").toString());
	    			numberOfWins++;
	    			jsonLineItem.put("numberOfWins", numberOfWins);
	    		}
	    		}
	    		
	    	}
		File file = new File("Users.json");
		try {
			file.createNewFile();
			FileWriter filewriter = new FileWriter(file);
			filewriter.write(users.toJSONString());
			filewriter.flush();
			filewriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
   	
	}




	public void assignResources() {
		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).getPlayerColor().equals(Color.Black)){
				players.get(i).getMyPersonalBoard().setMyPossession(new Possession(5+i, 5+i, 5+i, 5+i, 0, 0, 5));
			}
		}
		
	}




	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public static int getCurrentNumberOfGame() {
		return currentNumberOfGame;
	}

	public static void setCurrentNumberOfGame(int currentNumberOfGame) {
		Game.currentNumberOfGame = currentNumberOfGame;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getSpecificPlayer(Color color) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getPlayerColor().equals(color)) {
				return players.get(i);
			}
		}
		return null;
	}

	public void addPlayers(Player player) {
		this.players.add(player);
		numberOfPlayers ++;
	}

	public Age getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(Age currentAge) {
		this.currentAge = currentAge;
	}
	

	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String toString() {
		String string = new String("game: " + id + " players: " + numberOfPlayers + " host: " + host + "\n");
		return string;
	}



	public void generateRanking() {
		for (int i = 0; i < victoryPointsRanking.size(); i++) {
			for (int j = i; j < victoryPointsRanking.size(); j++){
				int victoryPointsI = victoryPointsRanking.get(i).getMyPersonalBoard().getMyPossession().getVictoryPoints().getValue();
				int victoryPointsJ = victoryPointsRanking.get(j).getMyPersonalBoard().getMyPossession().getVictoryPoints().getValue();
				if (victoryPointsI < victoryPointsJ){
					Player tmp = victoryPointsRanking.get(i);
					victoryPointsRanking.set(i, victoryPointsRanking.get(j));
					victoryPointsRanking.set(j, tmp);
				}
			}
		}
		for (int i = 0; i < militaryPointsRanking.size(); i++) {
			for (int j = i; j < militaryPointsRanking.size(); j++){
				int militaryPointsI = militaryPointsRanking.get(i).getMyPersonalBoard().getMyPossession().getMilitaryPoints().getValue();
				int militaryPointsJ = militaryPointsRanking.get(j).getMyPersonalBoard().getMyPossession().getMilitaryPoints().getValue();
				if (militaryPointsI < militaryPointsJ){
					Player tmp = militaryPointsRanking.get(i);
					militaryPointsRanking.set(i, militaryPointsRanking.get(j));
					militaryPointsRanking.set(j, tmp);
				}
			}
		}
		
	}




	public void resetPlayedLeaders() {
		for (int i = 0; i < players.size(); i++) {
			players.get(i).getMyPersonalBoard().refreshOncePerTurnLeaders();
		}
	}



}