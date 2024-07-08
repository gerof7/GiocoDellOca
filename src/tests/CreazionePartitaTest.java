package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import GiocoDellOca.GiocoDellOca;

class CreazionePartitaTest {

	@Test
	void creaPartita() {
		var controller = new GiocoDellOca();
		controller.configuraNuovaPartitaSP();
		
		var impostazioni = controller.getPartitaCorrente().getImpostazioni();
		
		//Caso_regole_singole
		impostazioni.addRegolaToList(controller.getListaRegoleSingole().get(0));
		impostazioni.setScenario(controller.getListaScenari().get(0));
		impostazioni.addPersonalizzazioneToList(controller.getListaPersonalizzazioni().get(0));
		
		var partita = controller.getPartitaCorrente();
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getElencoPersonalizzazioni(), impostazioni.getElencoPersonalizzazioni());
		
		//Caso_setRegole
		impostazioni.getElencoRegole().clear();
		impostazioni.getElencoPersonalizzazioni().clear();
		
		var mapRegoleSet = controller.getMapRegoleSet();
		
		for (var entry : mapRegoleSet.entrySet()) {
			if (entry.getKey() == "Set regole 1") {
				for (var regola : entry.getValue()) {
					impostazioni.addRegolaToList(regola);
				}
			}else {break;}
		}
		
		//Caso_Pedina
		impostazioni.addPersonalizzazioneToList(controller.getListaPersonalizzazioni().get(0));
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getElencoPersonalizzazioni(), impostazioni.getElencoPersonalizzazioni());
		
		
	}

}
