package it.polimi.ingsw.GC_21.view;

import it.polimi.ingsw.GC_21.ACTION.ExcommAction;

public class ExcommInput extends InputFromView {

	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		adapter.out("Bergoglio wants to know if you have been a great guy recently! \n"
			+ "Be careful: if you disappoint him, you will get a permanent malus!!!" + 
			"\nDo you want to be excommunicated? (Y) - (N)");
		this.choice(remoteView);
		
	}


	public void choice(RemoteView remoteView){
		String choiche = adapter.in();
		switch (choiche) {
		case "Y" : remoteView.notifyObservers(new ExcommAction(remoteView.getPlayer(), remoteView.getGame(), true));
					break;
		case "N" :	remoteView.notifyObservers(new ExcommAction(remoteView.getPlayer(), remoteView.getGame(), false));
					break;
		default: adapter.out("Sorry, Sua Eccellenza didn't get your answer... could you repeat please?");
				this.choice(remoteView);
		}
	}
}
