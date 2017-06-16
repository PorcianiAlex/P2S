package it.polimi.ingsw.GC_21.UTILITIES;

import it.polimi.ingsw.GC_21.view.InputForm;

public interface CurrentObserver {

		public void updateCurrent(InputForm inputFromView);
		public void updateString(String comunication);
}
