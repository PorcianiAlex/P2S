package it.polimi.ingsw.GC_21.CLIENT;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InputForm;

public interface RmiClientInterface extends Remote, Connections {
	public void clientReceive(String string)throws RemoteException;
	public void receiveObject(MessageToClient messageToClient) throws RemoteException;
	public InputForm sendObjectToServer() throws RemoteException;
	

	

}
