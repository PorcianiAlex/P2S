package it.polimi.ingsw.GC_21.CLIENT;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.REMOTEVIEW.InputForm;

public interface RmiClientInterface extends Remote, Connections {
	public void receiveObject(MessageToClient messageToClient) throws RemoteException;
	public InputForm sendObjectToServer() throws RemoteException;


	

}
