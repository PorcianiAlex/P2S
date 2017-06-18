package it.polimi.ingsw.GC_21.VIEW;

import java.io.PrintStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.CLIENT.CheckLoginMessage;
import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;

public interface AdapterConnection {

       public String in();
       public void out(String string);
       public void sendObject(MessageToClient messageToClient);
       public InputForm receiveObject(); 
	
	
}
