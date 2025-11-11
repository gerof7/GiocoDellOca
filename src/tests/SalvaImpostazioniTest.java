package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.Test;

import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.Pedina;
import GiocoDellOca.Regola;

class SalvaImpostazioniTest {

	@Test
	void impostazioniTest() {
		var giocoDellOca = new GiocoDellOca();
		
		giocoDellOca.salvaImpostazioni("G1", "G2", "G3", "G4", "cod_set_default", "cod_scen_default", "D1", "D2", "D3", "D4", "P1", "P2", "P3", "P4");
		
	    assertNotNull(giocoDellOca.getNomiGiocatoriDefault(), "La mappa nomiGiocatoriDefault non deve essere null");
	    assertNotNull(giocoDellOca.getCodiciDadiGiocatoriDefault(), "La mappa codiciDadiGiocatoriDefault non deve essere null");
	    assertNotNull(giocoDellOca.getCodiciPedineGiocatoriDefault(), "La mappa codiciPedineGiocatoriDefault non deve essere null");
	    assertNotNull(giocoDellOca.getCodiceRegoleSetDefault(), "Il codice del set di regole default non deve essere null");
	    assertNotNull(giocoDellOca.getCodiceScenarioDefault(), "Il codice dello scenario default non deve essere null");

	    assertFalse(giocoDellOca.getNomiGiocatoriDefault().isEmpty(), "La mappa nomiGiocatoriDefault non deve essere vuota");
	    assertFalse(giocoDellOca.getCodiciDadiGiocatoriDefault().isEmpty(), "La mappa codiciDadiGiocatoriDefault non deve essere vuota");
	    assertFalse(giocoDellOca.getCodiciPedineGiocatoriDefault().isEmpty(), "La mappa codiciPedineGiocatoriDefault non deve essere vuota");

	    assertEquals("G1", giocoDellOca.getNomiGiocatoriDefault().get(1));
	    assertEquals("G2", giocoDellOca.getNomiGiocatoriDefault().get(2));
	    assertEquals("G3", giocoDellOca.getNomiGiocatoriDefault().get(3));
	    assertEquals("G4", giocoDellOca.getNomiGiocatoriDefault().get(4));

	    assertEquals("cod_set_default", giocoDellOca.getCodiceRegoleSetDefault());
	    assertEquals("cod_scen_default", giocoDellOca.getCodiceScenarioDefault());

	    assertEquals("D1", giocoDellOca.getCodiciDadiGiocatoriDefault().get(1));
	    assertEquals("P1", giocoDellOca.getCodiciPedineGiocatoriDefault().get(1));

	}
	//imposta Regole singole
	@Test
	void impostaRegoleSingoleTest() {
		var numeroRegoleAttese = 2;
		javax.swing.table.TableModel tableModel = null;
		
		
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		giocoDellOca.impostaRegoleSingole(tableModel);
		
		//Caso in cui la table è vuota: riempimento automatico elenco regole
		List<Regola> elencoRegole = giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole();
		assertEquals(numeroRegoleAttese, elencoRegole.size());
		//Caso in cui la table non è vuota
		String[] colonne = {"Codice", "Descrizione", "Proprieta", "Tipologia"};
		tableModel = new DefaultTableModel(colonne, 0);
		Object[] record1 = {"Cod1", "Des1", "Prop1", null};
		Object[] record2 = {"Cod2", "Des2", "Prop2", null};
		((DefaultTableModel) tableModel).addRow(record1);
		((DefaultTableModel) tableModel).addRow(record2);
		assertEquals(numeroRegoleAttese, tableModel.getRowCount());
		giocoDellOca.impostaRegoleSingole(tableModel);
		assertEquals(numeroRegoleAttese, elencoRegole.size());
	}
	//imposta Regole da set
	@Test
	void impostaRegoleDaSetTest() {
		
		var numeroRegoleAttese = 2;
		javax.swing.table.TableModel tableModel = null;
		
		
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		giocoDellOca.impostaRegoleDaSet(tableModel);
		
		//Caso in cui la table è vuota: riempimento automatico elenco regole
		var elencoRegole = giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole();
		assertEquals(numeroRegoleAttese, elencoRegole.size());
		
		//Caso in cui la table non è vuota
		String[] colonne = {"Codice", "Descrizione", "Proprieta", "Tipologia"};
		tableModel = new DefaultTableModel(colonne, 0);
		Object[] record1 = {"Cod1", "Des1", "Prop1", null};
		Object[] record2 = {"Cod2", "Des2", "Prop2", null};
		((DefaultTableModel) tableModel).addRow(record1);
		((DefaultTableModel) tableModel).addRow(record2);
		assertEquals(numeroRegoleAttese, tableModel.getRowCount());
		giocoDellOca.impostaRegoleSingole(tableModel);
		assertEquals(numeroRegoleAttese, elencoRegole.size());
	}
	
	//imposta scenario
	@Test
	void impostaScenarioTest() {
		
		var giocoDellOca = new GiocoDellOca();
		
		var codiceScenarioTest = giocoDellOca.getListaScenari().get(0).getCodiceScenario();
		giocoDellOca.configuraNuovaPartita();
		giocoDellOca.impostaScenario(codiceScenarioTest);
		var scenario = giocoDellOca.getPartitaCorrente().getImpostazioni().getScenario();
		assertEquals(codiceScenarioTest, scenario.getCodiceScenario());
		
		//Se si imposta uno scenario diverso da quello definito nella lista non viene inserito, ma viene preso lo scenario di default
		codiceScenarioTest = "TestNonPresente";
		giocoDellOca.impostaScenario(codiceScenarioTest);
		scenario = giocoDellOca.getPartitaCorrente().getImpostazioni().getScenario();
		assertNotEquals(codiceScenarioTest, scenario.getCodiceScenario());
		assertEquals("SCN_DEFAULT", scenario.getCodiceScenario());
		
		
	}
	//imposta pedina
	@Test
	void impostaPedinaTest() {
		
		var giocoDellOca = new GiocoDellOca();
		
		var codicePedinaTest = giocoDellOca.getListaPersonalizzazioni().get(3).getCodicePersonalizzazione();
		giocoDellOca.configuraNuovaPartita();
		var giocatore = giocoDellOca.getPartitaCorrente().getAllGiocatori().get(1);
		giocoDellOca.impostaPedinaGiocatore(codicePedinaTest, giocatore.getNumero(), true);
		var codicePedina = giocoDellOca.getPartitaCorrente().getGiocatore(giocatore.getNumero()).getPedina().getCodicePersonalizzazione();
		assertEquals(codicePedinaTest, codicePedina);
	}
	//imposta dado
	@Test
	void impostaDadoTest() {
		
		var giocoDellOca = new GiocoDellOca();
		
		var codiceDadoTest = "Dado_Rosso";
		giocoDellOca.configuraNuovaPartita();
		var giocatore = giocoDellOca.getPartitaCorrente().getAllGiocatori().get(1);
		giocoDellOca.impostaDadoGiocatore(codiceDadoTest, giocatore.getNumero(), true);
		var codiceDado = giocoDellOca.getPartitaCorrente().getGiocatore(giocatore.getNumero()).getDado().getCodicePersonalizzazione();
		assertEquals(codiceDadoTest, codiceDado);
	}
	//esegui turno
	
	@Test
	void eseguiTurnoTest() {
		
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		
		var codiceScenarioTest = giocoDellOca.getListaScenari().get(0).getCodiceScenario();
		giocoDellOca.impostaScenario(codiceScenarioTest);
		
		giocoDellOca.avviaPartita();
		var giocatore = giocoDellOca.getPartitaCorrente().getAllGiocatori().get(1);
		var mossa = giocoDellOca.eseguiTurnoGiocatore(giocatore.getPedina(), 2);
		assertFalse(mossa.toString().isEmpty());
		
	}
	//aggiungi giocatori ospiti
	
	//configura giocatore corrente
	

}
