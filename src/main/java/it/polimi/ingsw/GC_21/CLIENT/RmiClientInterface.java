package it.polimi.ingsw.GC_21.CLIENT;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiClientInterface extends Remote {
	public void clientReceive(String string)throws RemoteException;
	public String sendToServer() throws RemoteException;
}
