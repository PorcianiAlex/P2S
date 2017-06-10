package it.polimi.ingsw.GC_21.CLIENT;

import java.awt.image.TileObserver;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.fx.ViewType;
import it.polimi.ingsw.GC_21.view.ServerForSocket;
import it.polimi.ingsw.GC_21.view.ServerInterface;


public class RmiClient extends UnicastRemoteObject implements Serializable, RmiClientInterface, Connections{

	private ArrayList<String> messages;
	private ViewType view;
	
	public RmiClient(ViewType view) throws RemoteException {
		super();
		this.view=view;
		this.messages = new ArrayList<String>();
	}

	public void clientReceive(String string) {
		if ("music".equals(string)) {
			Music.start();
		} 
		System.out.println(string);
		
	}
	
	public String sendToServer() throws RemoteException {
		if(view.equals(ViewType.GUI)) {
		String toserver = new String(messages.get(0));
		messages.remove(0);
		return toserver;
		} else {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
		}
	}


	public void sendGUI(String mess) {
		messages.add(mess);
	}
	
	
  
	
}
