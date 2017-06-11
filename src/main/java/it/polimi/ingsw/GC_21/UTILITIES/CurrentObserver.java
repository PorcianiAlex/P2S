package it.polimi.ingsw.GC_21.UTILITIES;

import it.polimi.ingsw.GC_21.view.InputFromView;

public interface CurrentObserver {

		public void updateCurrent(InputFromView inputFromView);
		public void updateString(String comunication);
}
