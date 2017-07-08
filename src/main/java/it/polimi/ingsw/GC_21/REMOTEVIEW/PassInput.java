package it.polimi.ingsw.GC_21.REMOTEVIEW;

import it.polimi.ingsw.GC_21.ACTION.Pass;

public class PassInput extends InputForm {
	
	@Override
	public void execute(RemoteView remoteView) {
		Pass pass = new Pass(remoteView.getPlayer());
		remoteView.notifyObservers(pass);
	}

}
