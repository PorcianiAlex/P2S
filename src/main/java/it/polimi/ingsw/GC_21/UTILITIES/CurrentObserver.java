package it.polimi.ingsw.GC_21.UTILITIES;

import it.polimi.ingsw.GC_21.CLIENT.MessageToClient;
import it.polimi.ingsw.GC_21.REMOTEVIEW.InputForm;

public interface CurrentObserver {

		public void updateCurrent(MessageToClient messageToClient);
}
