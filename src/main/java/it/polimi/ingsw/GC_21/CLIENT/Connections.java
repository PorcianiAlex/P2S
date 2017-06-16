package it.polimi.ingsw.GC_21.CLIENT;

import java.util.Scanner;

public interface Connections {
 public void sendGUI(String string);
 public String getMessage();
 public MessageToClient getReceivedMessage();
public Scanner getKeyboard();
 
}
