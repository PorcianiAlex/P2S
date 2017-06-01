package it.polimi.ingsw.GC_21.CLIENT;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.view.ServerForSocket;
import it.polimi.ingsw.GC_21.view.ServerInterface;


public class RmiClient extends UnicastRemoteObject implements Serializable, RmiClientInterface{

	
	
	protected RmiClient() throws RemoteException {
		super();
	}

	public void clientReceive(String string) {
		System.out.println(string);
	}
	
	public String sendToServer() throws RemoteException {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
  
	
}
