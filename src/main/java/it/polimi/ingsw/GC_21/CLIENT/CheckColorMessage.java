package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import it.polimi.ingsw.GC_21.VIEW.ActionInput;
import it.polimi.ingsw.GC_21.VIEW.InitGameInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;

public class CheckColorMessage extends MessageToClient implements Callable<InputForm> {
	private boolean host;
	private Object LOCK;
	private InitialTimerThread timerThread;

	public CheckColorMessage(boolean result, String description, boolean host) {
		super(result, false, description);
		this.host = host;
	}
	
	public CheckColorMessage(boolean result, boolean waiting, String description, boolean host) {
		super(result, waiting, description);
		this.host = host;
	}
	
	@Override
	public InputForm executeCLI(Object LOCK) throws InterruptedException {
		this.LOCK = LOCK;
		if (result) {
			if (host) {	
				ExecutorService poolExecutorService = Executors.newFixedThreadPool(1);
				Future future = poolExecutorService.submit(this);
				timerThread = new InitialTimerThread(client, future);
				timerThread.start();
				try {
					return (InputForm) future.get();
				} catch (InterruptedException | ExecutionException | CancellationException e) {
					System.out.println("Time exceeded, Now starts the game");
					return null;
				}
				
			}
			System.out.println(description);
			return null;
		}
		else {
			System.out.println(description);
			CheckLobbyMessage retryLobbyMessage = new CheckLobbyMessage(this, true, "Retry");
			return retryLobbyMessage.executeCLI(LOCK);
		}
	}
	
	
	@Override
	public InputForm call() throws Exception {
		inputForm = new InitGameInput();
		inputForm =  super.executeCLI(LOCK);
		timerThread.interrupt();
		return inputForm;
	}

	public boolean isHost() {
		return host;
	}
	public void setHost(boolean host) {
		this.host = host;
	}

	
	
}
