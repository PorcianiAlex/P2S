package it.polimi.ingsw.GC_21.UTILITIES;

import java.util.Observable;

import it.polimi.ingsw.GC_21.ACTION.Action;

public interface MyObserver {

	public boolean update(Action action);
	
}
