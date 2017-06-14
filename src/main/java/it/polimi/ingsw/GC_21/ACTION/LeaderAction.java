package it.polimi.ingsw.GC_21.ACTION;

import java.util.ArrayList;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.LeaderCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.OncePerTurnLeaderCard;
import it.polimi.ingsw.GC_21.PLAYER.Player;

public class LeaderAction extends Action {
	private boolean playOrTurn;
	private LeaderCard leaderChosen;

	public LeaderAction(Player playerInAction, LeaderCard leaderChosen, boolean playOrTurn) {
		super(playerInAction);
		this.playOrTurn = playOrTurn;
		this.leaderChosen = leaderChosen;
	}

	@Override
	public void Execute() {
		super.Execute();
		if (playOrTurn){
			if (playerInAction.getPlayedOncePerTurnLeaderCards().contains(leaderChosen)){
				((OncePerTurnLeaderCard) leaderChosen).callEffect(playerInAction);
				}			
			else{
				game.notifyCurrentString("You don't have any leader card to use!");
				}
			}
		else{
			if (leaderChosen.checkRequirements(playerInAction) && !leaderChosen.isPlayed()){
				leaderChosen.callEffect(playerInAction);
				leaderChosen.setPlayed(true);
				if (leaderChosen instanceof OncePerTurnLeaderCard){
					playerInAction.getPlayedOncePerTurnLeaderCards().add((OncePerTurnLeaderCard) leaderChosen);
				}
			}
			else{
				game.notifyCurrentString("You didn't satisfy the requirement to turn this card or it's already played!");
			}
		}
		}
	}

