package it.polimi.ingsw.GC_21.CLIENT;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.view.InputForm;

public interface RmiClientInterface extends Remote {
	public void clientReceive(String string)throws RemoteException;
	public String sendToServer() throws RemoteException;
	public void receiveObject(MessageToClient messageToClient) throws RemoteException;
	public InputForm sendObjectToServer() throws RemoteException;
	

	

}
