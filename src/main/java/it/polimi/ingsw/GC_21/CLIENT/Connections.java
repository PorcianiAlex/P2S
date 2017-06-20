package it.polimi.ingsw.GC_21.CLIENT;

import java.io.IOException;
import java.util.Scanner;

import it.polimi.ingsw.GC_21.VIEW.InputForm;

public interface Connections {
 public void sendGUI(String string);
 public String getMessage();
 public MessageToClient getReceivedMessage() throws ClassNotFoundException, IOException;
public Scanner getKeyboard();
public void sendInput(InputForm inputForm) throws IOException;
 
}
