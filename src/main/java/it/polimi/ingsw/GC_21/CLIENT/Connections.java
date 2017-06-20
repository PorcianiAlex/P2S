package it.polimi.ingsw.GC_21.CLIENT;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

public interface Connections extends Remote {
public void sendGUI(String string);
public String sendToServer() throws RemoteException;
public String getMessage();
public MessageToClient getReceivedMessage();
public Scanner getKeyboard();
public void sendInput(InputForm inputForm) throws IOException;
 
}
