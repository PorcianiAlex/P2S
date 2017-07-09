package it.polimi.ingsw.GC_21.VIEW;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import it.polimi.ingsw.GC_21.ACTION.ExcommAction;

public class ExcommInput extends InputForm {
	private String choice;

	public ExcommInput() {
	}
	
	public ExcommInput(String choice) {
		this.choice = choice;
	}


	@Override
	public void execute(RemoteView remoteView) {
		super.execute(remoteView);
		System.out.println("Do you want to be excommunicated? \n(Y) or (N)");
		switch (choice) {
		case "Y" : try {
				remoteView.notifyObservers(new ExcommAction(remoteView.getPlayer(), remoteView.getGame(), true));
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					break;
		case "N" :	try {
				remoteView.notifyObservers(new ExcommAction(remoteView.getPlayer(), remoteView.getGame(), false));
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					break;
		default: try {
				remoteView.notifyObservers(new ExcommAction(remoteView.getPlayer(), remoteView.getGame(), true));
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					break;
		}		
	}


@Override
public void inputFromCli() throws InterruptedException {
	choice = takeInput(this);
}
}
