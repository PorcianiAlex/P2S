package it.polimi.ingsw.GC_21.UTILITIES;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;

public interface P2SObserver<C> {
	

	public  boolean update(C change);
	public void updateControllerManager(String string);
	public void update(String string);
	public void updateMessage(Message message);
	public void updateTurn();
	public void updateInit();
	public void updateExcomm();
		
}
