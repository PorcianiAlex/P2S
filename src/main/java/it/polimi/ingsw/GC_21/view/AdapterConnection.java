package it.polimi.ingsw.GC_21.view;

import java.io.PrintStream;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;

public interface AdapterConnection {

       public String in();
       public void out(String string);
       public void sendObject(Message message);
       public InputFromView receiveObject(); 
	
	
}
