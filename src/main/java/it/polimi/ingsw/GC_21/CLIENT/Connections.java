package it.polimi.ingsw.GC_21.CLIENT;

import it.polimi.ingsw.GC_21.GAMEMANAGEMENT.Message;

public interface Connections {
 public void sendGUI(String string);
 public String getMessage();
 public Message getReceivedMessage();
 
}
