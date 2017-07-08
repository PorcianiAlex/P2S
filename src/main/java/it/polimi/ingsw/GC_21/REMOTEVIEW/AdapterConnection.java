package it.polimi.ingsw.GC_21.REMOTEVIEW;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.CLIENT.CheckLoginMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;

public interface AdapterConnection {
       public void sendObject(MessageToClient messageToClient);
       public InputForm receiveObject();
       public void close() throws IOException; 
	
	
}
