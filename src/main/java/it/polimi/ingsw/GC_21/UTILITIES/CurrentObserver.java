package it.polimi.ingsw.GC_21.UTILITIES;

import it.polimi.ingsw.GC_21.VIEW.InputForm;

public interface CurrentObserver {

		public void updateCurrent(InputForm inputFromView);
		public void updateString(String comunication);
}
