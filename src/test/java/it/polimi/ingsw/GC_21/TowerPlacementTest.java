package it.polimi.ingsw.GC_21;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_21.BOARD.Tower;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevCardType;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.DevelopmentCard;
import it.polimi.ingsw.GC_21.GAMECOMPONENTS.Possession;

public class TowerPlacementTest {

	@Test
	public void testPayPossession() {
		Tower test = new Tower(DevCardType.Venture);
		TowerPlacement tester = new TowerPlacement(test.getFloors()[1],
		Possession payment = new Possession(1, 1, 0, 0, 0, 0, 0, 0)
	}

}
