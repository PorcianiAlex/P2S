package it.polimi.ingsw.GC_21.CLIENT;

public class TimerThread extends Thread {
	private boolean outOfTime;

	public TimerThread(boolean outOfTime) {
		this.outOfTime = outOfTime;
	}
	
	@Override
	public void run() {
		try {
			this.sleep(10000);
			outOfTime=true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
