package it.polimi.ingsw.GC_21.VIEW;

public class SaveInput extends InputForm{
	
	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		remoteView.notifySave(remoteView.getUsername());
	}

}
