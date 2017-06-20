package it.polimi.ingsw.GC_21.ACTION;

import java.util.ArrayList;



import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
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
			if (playerInAction.getMyPersonalBoard().getPlayedOncePerTurnLeaderCards().contains(leaderChosen)){
				((OncePerTurnLeaderCard) leaderChosen).callEffect(playerInAction);
				}			
			/*else{
				game.notifyCurrentString("You don't have any leader card to use!");
				}*/
			}
		else{ //if I have to turn a new leader Card
			if (leaderChosen.checkRequirements(playerInAction) && !leaderChosen.isPlayed()){
				leaderChosen.callEffect(playerInAction);
				leaderChosen.setPlayed(true);
				if (leaderChosen instanceof OncePerTurnLeaderCard){
					playerInAction.getMyPersonalBoard().getPlayedOncePerTurnLeaderCards().add((OncePerTurnLeaderCard) leaderChosen);
				}
				//game.notifyCurrentString("Leader Card activated!");
			}
			/*else{
				game.notifyCurrentString("You didn't satisfy the requirement to turn this card or it's already played!");
			}*/
		}
		}
	}
}

