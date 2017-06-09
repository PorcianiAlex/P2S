package it.polimi.ingsw.GC_21.UTILITIES;

import java.awt.List;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;
import it.polimi.ingsw.GC_21.UTILITIES.*;
import it.polimi.ingsw.GC_21.view.RemoteView;

import java.util.*;

import javax.naming.spi.DirStateFactory.Result;
import javax.sound.sampled.LineListener;

public abstract class Observable<C> {
	private ArrayList<P2SObserver<C>> observers;
	
	public Observable() {

		observers = new ArrayList<P2SObserver<C>>();
	}

	public void attach(P2SObserver<C> o) {
		observers.add(o);
	}

	public void detach(P2SObserver<C> o) {
		this.observers.remove(o);
	}

	
	
	
	
	public void notifyTurn() {	
		for (P2SObserver<C> o : this.observers) {
			o.updateTurn();
		}
	}

	public void notifyExcomm() {	
		for (P2SObserver<C> o : this.observers) {
			o.updateExcomm();
		}
	}
	
	
	public void notifyString(String string) {	
		for (P2SObserver<C> o : this.observers) {
			o.update(string);
		}
	}
	
	public void notifyControllerManager(String string) {	
		for (P2SObserver<C> o : this.observers) {
			o.updateControllerManager(string);
		}
	}
	
	public void notifyMessage(Message message) {	
		for (P2SObserver<C> o : this.observers) {
			o.updateMessage(message);
		}
	}

	public boolean notifyObservers(C c) {
		for (P2SObserver<C> o : this.observers) {
			boolean result = o.update(c);
			if (!result) {
				return false;
			}
		}
		return true;

	}
	
	public void notifyInit() {
		for (P2SObserver<C> o : this.observers) {
			o.updateInit();
		}
	}

}
