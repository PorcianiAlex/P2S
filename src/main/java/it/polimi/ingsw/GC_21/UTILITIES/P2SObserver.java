package it.polimi.ingsw.GC_21.UTILITIES;

import it.polimi.ingsw.GC_21.ACTION.Action;

public interface P2SObserver<C> {
	

	public  boolean update(C change);
	public void update(String string);
	public boolean update();
		
}
