package it.polimi.ingsw.GC_21.UTILITIES;

import it.polimi.ingsw.GC_21.ACTION.Action;
import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;
import it.polimi.ingsw.GC_21.view.ExcommInput;
import it.polimi.ingsw.GC_21.view.InputFromView;

public interface P2SObserver<C> {
	

	public  boolean update(C change);
	public void updateControllerManager(String string);
	public void update(String string);
	public boolean updateMessage(Message message);
	public void updateTurn();
	public void updateCurrent(InputFromView inputFromView);
	public void updateInit();
	void updateExcomm(ExcommInput excommInput);
	void updateExcomm();
		
}
