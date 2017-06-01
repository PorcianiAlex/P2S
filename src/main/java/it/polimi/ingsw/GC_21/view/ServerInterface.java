package it.polimi.ingsw.GC_21.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.RmiClientInterface;


public interface ServerInterface extends Remote{
	 void join(RmiClientInterface rmiClient) throws RemoteException;
	 public String serverReceive(String string) throws RemoteException;
}
