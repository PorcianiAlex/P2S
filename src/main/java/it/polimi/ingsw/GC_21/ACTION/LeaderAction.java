package it.polimi.ingsw.GC_21.ACTION;

import java.util.ArrayList;



import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.PermanentLeaderCard;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Game;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class LeaderAction extends Action {
	private boolean turningLeaderCard;
	private LeaderCard leaderChosen;

	public LeaderAction(Player playerInAction, LeaderCard leaderChosen, boolean turningLeaderCard, Game game) {
		super(playerInAction);
		this.turningLeaderCard = turningLeaderCard;
		this.leaderChosen = leaderChosen;
		this.game = game;
	}

	@Override
	public void Execute() {
		super.Execute();
		if (leaderChosen!=null){
			if (!turningLeaderCard){ //If I choose to play a card already turned
			String leaderName = leaderChosen.getName();
			for (int i = 0; i < playerInAction.getMyPersonalBoard().getLeaderCards().size(); i++) {
				if (playerInAction.getMyPersonalBoard().getLeaderCards().get(i).getName().equals(leaderName)){
					((OncePerTurnLeaderCard) leaderChosen).callEffect(playerInAction);
				return;
				}
			}
			System.out.println(playerInAction.getName() + " failed playing a leaderCard");
			}
				
			
		else{ //if I have to turn a new leader Card
			if (leaderChosen.checkRequirements(playerInAction) && !leaderChosen.isPlayed()){
				leaderChosen.callEffect(playerInAction);
				leaderChosen.setPlayed(true);
				if (leaderChosen instanceof OncePerTurnLeaderCard){
					playerInAction.getMyPersonalBoard().getPlayedOncePerTurnLeaderCards().add((OncePerTurnLeaderCard) leaderChosen);
				}
		
				System.out.println(playerInAction.getName() + " just activated a leader card!");
			}
			else{
				System.out.println(playerInAction.getName() + " failed turning a leader card!");
			}
		}
		}
	}
}

