package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InputForm;

public interface Connections extends Remote{
public MessageToClient getReceivedMessage() throws ClassNotFoundException, RemoteException, IOException;
public void sendInput(InputForm inputForm) throws IOException, RemoteException;
public void close() throws IOException;
 
}
