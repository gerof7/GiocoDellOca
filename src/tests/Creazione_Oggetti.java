package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import GiocoDellOca.Giocatore;

class Creazione_Oggetti {

	@Test
	void creaGiocatoritest() {
		//fail("Not yet implemented");
		int numPlayers = 5;
		
		for(int i = 0; i < numPlayers; i++) {
			Giocatore giocatore1 = new Giocatore("Player"+i, i);
			assertEquals(giocatore1.getId(), i);
		}
	}

}
