package it.polimi.ingsw.GC_21.CLIENT;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;
import it.polimi.ingsw.GC_21.view.InputFromView;

public interface RmiClientInterface extends Remote {
	public void clientReceive(String string)throws RemoteException;
	public String sendToServer() throws RemoteException;
	public void receiveObject(Message message) throws RemoteException;
	public InputFromView sendObjectToServer() throws RemoteException;

}
