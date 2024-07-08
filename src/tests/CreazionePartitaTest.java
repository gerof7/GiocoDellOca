package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.Regola;
import GiocoDellOca.TipologiaPersonalizzazioneEnum;
import GiocoDellOca.TipologiaRegoleEnum;

class CreazionePartitaTest {

	@Test
	void creaPartita() {
		var controller = new GiocoDellOca();
		controller.configuraNuovaPartitaSP();
		
		var impostazioni = controller.getPartitaCorrente().getImpostazioni();
		
		//Caso_regole_singole
		impostazioni.setTipologiaRegole(TipologiaRegoleEnum.RegolaSingola);
		impostazioni.addRegolaToList(controller.getListaRegoleSingole().get(0));
		
		impostazioni.setScenario(controller.getListaScenari().get(0));
		
		impostazioni.setTipologiaPersonalizzazione(TipologiaPersonalizzazioneEnum.Dado);
		impostazioni.addPersonalizzazioneToList(controller.getListaPersonalizzazioni().get(0));
		
		var partita = controller.getPartitaCorrente();
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getTipologiaRegole(), impostazioni.getTipologiaRegole());
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getTipologiaPersonalizzazione(), impostazioni.getTipologiaPersonalizzazione());
		assertEquals(partita.getImpostazioni().getElencoPersonalizzazioni(), impostazioni.getElencoPersonalizzazioni());
		
		//Caso_setRegole
		impostazioni.getElencoRegole().clear();
		impostazioni.getElencoPersonalizzazioni().clear();
		
		var mapRegoleSet = controller.getMapRegoleSet();
		List<Regola> listaRegole = new ArrayList<Regola>();
		
		for (var entry : mapRegoleSet.entrySet()) {
			if (entry.getKey() == "Set regole 1") {
				for (var regola : entry.getValue()) {
					listaRegole.add(regola);
				}
			}else {break;}
		}
		
		impostazioni.setTipologiaRegole(TipologiaRegoleEnum.SetRegole);
		impostazioni.setListaRegole(listaRegole);
		
		//Caso_Pedina
		impostazioni.setTipologiaPersonalizzazione(TipologiaPersonalizzazioneEnum.Pedina);
		impostazioni.addPersonalizzazioneToList(controller.getListaPersonalizzazioni().get(0));
		
		assertNotNull(partita);
		assertEquals(partita.getImpostazioni().getTipologiaRegole(), impostazioni.getTipologiaRegole());
		assertEquals(partita.getImpostazioni().getElencoRegole(), impostazioni.getElencoRegole());
		
		assertEquals(partita.getImpostazioni().getScenario(), impostazioni.getScenario());
		assertEquals(partita.getImpostazioni().getTipologiaPersonalizzazione(), impostazioni.getTipologiaPersonalizzazione());
		assertEquals(partita.getImpostazioni().getElencoPersonalizzazioni(), impostazioni.getElencoPersonalizzazioni());
		
		
	}

}
