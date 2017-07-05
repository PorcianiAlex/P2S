package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import it.polimi.ingsw.GC_21.PLAYER.Player;
import it.polimi.ingsw.GC_21.VIEW.ActionInput;
import it.polimi.ingsw.GC_21.VIEW.InputForm;
import it.polimi.ingsw.GC_21.fx.FXMLGameController;

public class ChooseActionMessage extends MessageToClient implements Callable<InputForm>{
	private Player player;
	private TimerThread timerThread;
	
	

	public ChooseActionMessage(boolean result, String description, Player player) {
		super(result, true, description);
		this.player = player;
	}
	
	@Override
	public InputForm executeCLI(Object LOCK) throws InterruptedException {
		System.out.println(description);
		this.inputForm = new ActionInput();
		ExecutorService poolExecutorService = Executors.newFixedThreadPool(1);
		Future future = poolExecutorService.submit(this);
		timerThread = new TimerThread(client, future);
		timerThread.start();
		try {
			return (InputForm) future.get();
		} catch (InterruptedException | ExecutionException | CancellationException e) {
			System.out.println("Time exceeded, return pass input");
			return null;
		}
		
		}
	

	@Override
	public void executeGUI(FXMLGameController gameController) {
		ExecutorService poolExecutorService = Executors.newFixedThreadPool(1);
		timerThread = new TimerThread(client);
		timerThread.start();
		gameController.ifChooseAction(result, description, player, timerThread);
		
	}

	@Override
	public InputForm call() throws Exception {
			ActionInput actionInput = (ActionInput) inputForm;
			InputForm inputAction = actionInput.chooseAction(player, timerThread);
			timerThread.interrupt();
			return inputAction;	
	}

	
}
