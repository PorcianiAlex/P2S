package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.ACTION.Pass;

public class PassInput extends InputFromView {
	
	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		Pass pass = new Pass(remoteView.getPlayer());
		remoteView.notifyObservers(pass);
	}

}
