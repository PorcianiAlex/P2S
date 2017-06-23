package it.polimi.ingsw.GC_21.VIEW;

public class ActionTimer extends Thread {
	private ActionInput actionInput;
	

	
	public ActionTimer(ActionInput actionInput) {
		super();
		this.actionInput = actionInput;
	}



	@Override
	public void run() {
		try {
			sleep(10000);
			actionInput.setOutOfTime(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
