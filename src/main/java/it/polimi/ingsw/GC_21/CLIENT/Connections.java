package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InputForm;

public interface Connections extends Remote{
public void sendGUI(String string) throws RemoteException;
public String getMessage() throws RemoteException;
public MessageToClient getReceivedMessage() throws ClassNotFoundException, RemoteException, IOException;
public Scanner getKeyboard()throws RemoteException;
public void sendInput(InputForm inputForm) throws IOException, RemoteException;
public void sendString() throws RemoteException;
public void setKeyboard(Scanner keyboard)throws RemoteException;

 
}
