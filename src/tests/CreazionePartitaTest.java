package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import GiocoDellOca.GiocoDellOca;

class CreazionePartitaTest {

	@Test
	void creaPartita() {
		var controller = new GiocoDellOca();
		controller.configuraNuovaPartita();
		
		var impostazioni = controller.getPartitaCorrente().getImpostazioni();
		
	    impostazioni.impostaRegoleSingole(controller.getListaRegoleSingole(), null);
		impostazioni.setScenario(controller.getListaScenari().get(0));
		impostazioni.addPersonalizzazioneToList(controller.getListaPersonalizzazioni().get(0));
		
		var partita = controller.getPartitaCorrente();
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getElencoPersonalizzazioni(), impostazioni.getElencoPersonalizzazioni());
		
		impostazioni.getElencoRegole().clear();
		impostazioni.getElencoPersonalizzazioni().clear();
		
		var mapRegoleSet = controller.getMapRegoleSet();

	    impostazioni.impostaRegoleDaSet(mapRegoleSet, null);
	    var setClassico = mapRegoleSet.get("Partita classica");
	    
		impostazioni.addPersonalizzazioneToList(controller.getListaPersonalizzazioni().get(0));
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getElencoPersonalizzazioni(), impostazioni.getElencoPersonalizzazioni());
		
		assertNotNull(setClassico);
	    assertFalse(setClassico.isEmpty());
		
		
		
	}

}
