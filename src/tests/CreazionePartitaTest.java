package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import GiocoDellOca.GiocoDellOca;

class CreazionePartitaTest {

	@Test
	void creaPartita() {
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		
		var impostazioni = giocoDellOca.getPartitaCorrente().getImpostazioni();
		
	    impostazioni.impostaRegoleSingole(giocoDellOca.getListaRegoleSingole(), null);
		impostazioni.setScenario(giocoDellOca.getListaScenari().get(0));
		impostazioni.addPersonalizzazioneToList(giocoDellOca.getListaPersonalizzazioni().get(0));
		
		var partita = giocoDellOca.getPartitaCorrente();
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getElencoPersonalizzazioni(), impostazioni.getElencoPersonalizzazioni());
		
		impostazioni.getElencoRegole().clear();
		impostazioni.getElencoPersonalizzazioni().clear();
		
		var mapRegoleSet = giocoDellOca.getMapRegoleSet();

	    impostazioni.impostaRegoleDaSet(mapRegoleSet, null);
	    var setClassico = mapRegoleSet.get("Partita classica");
	    
		impostazioni.addPersonalizzazioneToList(giocoDellOca.getListaPersonalizzazioni().get(0));
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getElencoPersonalizzazioni(), impostazioni.getElencoPersonalizzazioni());
		
		assertNotNull(setClassico);
	    assertFalse(setClassico.isEmpty());
		
		
		
	}

}
