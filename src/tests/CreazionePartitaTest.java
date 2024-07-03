package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.TipologiaPersonalizzazioneEnum;
import GiocoDellOca.TipologiaRegoleEnum;

class CreazionePartitaTest {

	@Test
	void creaPartita() {
		var controller = new GiocoDellOca();
		controller.configuraNuovaPartitaSP();
		
		var impostazioni = controller.getPartitaCorrente().getImpostazioni();
		
		impostazioni.setTipologiaRegole(TipologiaRegoleEnum.RegolaSingola);
		impostazioni.addRegolaToList(controller.getListaRegoleSingole().get(0));
		
		impostazioni.setScenario(controller.getListaScenari().get(0));
		
		impostazioni.setTipologiaPersonalizzazione(TipologiaPersonalizzazioneEnum.Dado);
		impostazioni.addPersonalizzazioneToList(controller.getListaPersonalizzazioni().get(0));
		
		var partita = controller.getPartitaCorrente();
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getTipologiaPersonalizzazione(), impostazioni.getTipologiaPersonalizzazione());
	}

}
