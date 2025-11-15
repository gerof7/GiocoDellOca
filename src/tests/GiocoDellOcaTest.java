package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.table.DefaultTableModel;
import org.junit.jupiter.api.Test;

import GiocoDellOca.Dado;
import GiocoDellOca.GiocoDellOca;
import GiocoDellOca.Pedina;
import GiocoDellOca.Personalizzazione;
import GiocoDellOca.Regola;
import GiocoDellOca.Scenario;
import GiocoDellOca.TipologiaRegolaEnum;

class GiocoDellOcaTest {

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
		javax.swing.table.TableModel tableModel = new DefaultTableModel();
		
		
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		giocoDellOca.impostaRegoleSingole(tableModel);
		
		//Caso in cui la table è vuota: la lista di regole salvate nelle impostazioni sarà vuota
		List<Regola> elencoRegole = giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole();
		assertEquals(0, elencoRegole.size());
		
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
		javax.swing.table.TableModel tableModel = new DefaultTableModel();
		
		
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		giocoDellOca.impostaRegoleDaSet(tableModel);
		
		//Caso in cui la table è vuota: la lista di regole salvate nelle impostazioni sarà vuota
		var elencoRegole = giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoRegole();
		assertEquals(0, elencoRegole.size());
		
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
	
	    var gioco = new GiocoDellOca();
	    gioco.configuraNuovaPartita();
	
	    var codiceScenario = gioco.getListaScenari().get(0).getCodiceScenario();
	    gioco.impostaScenario(codiceScenario);
	
	    gioco.avviaPartita();
	
	    var partita = gioco.getPartitaCorrente();
	    var pedina = partita.getAllGiocatori().get(1).getPedina();
	
	    //Test su tabellone da 63 caselle
	
	    //Oca
	    pedina.setPosizione(1);       
	    gioco.eseguiTurnoGiocatore(pedina, 4);
	
	    assertEquals(9, pedina.getPosizione()); 
	    assertEquals(1, pedina.getStato());
	
	
	    //Ponte
	    pedina.setPosizione(1);      
	    gioco.eseguiTurnoGiocatore(pedina, 5);
	
	    int expectedPonte = 6 + 6;    
	    assertEquals(expectedPonte, pedina.getPosizione());
	    assertEquals(1, pedina.getStato());
	
	
	    //Locanda
	    pedina.setPosizione(17);      
	    pedina.setStato(1);          
	    gioco.eseguiTurnoGiocatore(pedina, 2);
	
	    assertEquals(19, pedina.getPosizione());
	    assertEquals(0, pedina.getStato());     
	
	    pedina.setStato(1); 
		
	    //Prigione
	    pedina.setPosizione(30);    
	    pedina.setStato(1);
	    gioco.eseguiTurnoGiocatore(pedina, 1);
	
	    assertEquals(31, pedina.getPosizione());
	    assertEquals(0, pedina.getStato());     
	
	    pedina.setStato(1);
		
	    //Labirinto
	    pedina.setPosizione(41);     
	    pedina.setStato(1);
	    gioco.eseguiTurnoGiocatore(pedina, 1);
	
	    assertEquals(39, pedina.getPosizione()); 
	    assertEquals(1, pedina.getStato());
	
	    
	    //Scheletro
	    pedina.setPosizione(57);     
	    pedina.setStato(1);
	    gioco.eseguiTurnoGiocatore(pedina, 1);
	
	    assertEquals(1, pedina.getPosizione()); 
	    assertEquals(1, pedina.getStato());
	
	
	    //Casella normale
	    pedina.setPosizione(10);
	    pedina.setStato(1);
	    gioco.eseguiTurnoGiocatore(pedina, 3);
	
	    assertEquals(13, pedina.getPosizione());
	    assertEquals(1, pedina.getStato());
	}

	//aggiungi giocatori ospiti
	@Test
	void aggiungiGiocatoriOspitiTest() {
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		giocoDellOca.aggiungiGiocatoriOspiti(2);	
		var giocatori = giocoDellOca.getPartitaCorrente().getAllGiocatori();
		
		assertEquals(giocatori.size(), 2);
	}
	//configura giocatore corrente
	@Test
	void configuraGiocatoreCorrenteTest() {
		String[] colonne = {"Codice", "Descrizione", "Proprieta", "Tipologia"};
		javax.swing.table.TableModel tablePedinaSelezionataModel = new DefaultTableModel(colonne, 0);
		javax.swing.table.TableModel tableDadoSelezionatoModel = new DefaultTableModel(colonne, 0);
		
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		var personalizzazioni = giocoDellOca.getPartitaCorrente().getImpostazioni().getElencoPersonalizzazioni();
		giocoDellOca.configuraGiocatoreCorrente(1, tablePedinaSelezionataModel, tableDadoSelezionatoModel, personalizzazioni);
		
		//Tabelle vuote
		var giocatore = giocoDellOca.getPartitaCorrente().getAllGiocatori().get(1);
		var giocatoreCorrente = giocoDellOca.getPartitaCorrente().getGiocatoreInSessione();
		var codicePedina = giocoDellOca.getPartitaCorrente().getGiocatore(giocatoreCorrente.getNumero()).getPedina().getCodicePersonalizzazione();
		var codiceDado = giocoDellOca.getPartitaCorrente().getGiocatore(giocatoreCorrente.getNumero()).getDado().getCodicePersonalizzazione();
		assertEquals(codicePedina, "Pedina_Oca");
		assertEquals(codiceDado, "Dado_Classico");
		assertEquals(giocatoreCorrente, giocatore);
	}
	
	//autenticazione admin
	@Test
	void autenticazioneAdminTest() {
		var giocoDellOca = new GiocoDellOca();
		//Caso corretto
		var resultAuth = giocoDellOca.autenticazioneAdmin("admin", "admin");
		assertEquals(resultAuth, true);
		//Caso errato
		resultAuth = giocoDellOca.autenticazioneAdmin("admin", "pluto");
		assertEquals(resultAuth, false);
		
	}
	//replace scenari
	@Test
	void replaceAllScenariTest() {
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		//Visualizzazione scenario di default
		var oldScenari = giocoDellOca.getListaScenari().size();
		//Replace dello scenario
		List<Scenario> nuoviScenari = new ArrayList<>();

		var scenarioTest = new Scenario("test", "Scenario per test replace Scenario",
				"La torta della nonna ha un profumo delizioso! (Avanzi del totale che hai appena ottenuto dal lancio dei dadi)",
				"Le indicazioni della mamma ti guidano lungo il sentiero del bosco! (Avanzi di un numero pari alla casella in cui ti trovi)",
				"Il lupo ti ferma per parlare! (Perdi 1 turno)",
				"Il lupo si è pappato Cappuccetto Rosso e la nonna! (Resti imprigionato finchè un altro giocatore non arriva su questa casella)",
				"State indietro! Il cacciatore è arrivato! (Torni indietro di 3 caselle) ",
				"Cappuccetto Rosso si è persa nel bosco! (Torni alla casella iniziale)");
		nuoviScenari.add(scenarioTest);
		giocoDellOca.replaceAllScenari(nuoviScenari);
		assertEquals(nuoviScenari.size(), giocoDellOca.getListaScenari().size());
		assertNotEquals(oldScenari, giocoDellOca.getListaScenari().size());
		
	}
	//replace regole
	@Test
	void replaceAllRegoleTest() {
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		//Visualizzazione set regole di default
		var oldSetRegole = giocoDellOca.getMapRegoleSet();
		
		//Creazione del nuovo set regole
		var regolaTest = new Regola("regolaTest", "Seleziona il numero delle caselle di cui sarà composto il tabellone di gioco", "", TipologiaRegolaEnum.NumeroCaselle);
		Set<Regola> regolaSetTest = new LinkedHashSet<>();
		Map<String, Set<Regola>> nuovoMapRegoleSet = new HashMap<>();
		regolaSetTest.add(regolaTest);
		nuovoMapRegoleSet.put("Map Regole Test", regolaSetTest);
		
		//Replace e confronto risultati
		giocoDellOca.replaceAllSetRegole(nuovoMapRegoleSet);
		assertEquals(nuovoMapRegoleSet.size(), giocoDellOca.getMapRegoleSet().size());
		assertNotEquals(oldSetRegole, giocoDellOca.getListaScenari().size());
	}
	//replace personalizzazioni
	@Test
	void replaceAllPersonalizzazioniTest() {
		var giocoDellOca = new GiocoDellOca();
		giocoDellOca.configuraNuovaPartita();
		//Visualizzazione personalizzazioni di default
		var oldPersonalizzazioni = giocoDellOca.getListaPersonalizzazioni();
		
		//Creazione della nuova personalizzazione
		List<Personalizzazione> nuovePersonalizzazioni = new ArrayList<>();

		var personalizzazioneTest1 = new Dado("Dado_RossoTest", "Il dado del giocatore è un dado nero e rosso", "./src/images/dadorosso_1.png");
		var personalizzazioneTest2 = new Pedina("Pedina_OcaTest", "La pedina del giocatore è l'oca classica", "./src/images/ScarfGoose.png");
		nuovePersonalizzazioni.add(personalizzazioneTest1);
		nuovePersonalizzazioni.add(personalizzazioneTest2);
		giocoDellOca.replaceAllPersonalizzazioni(nuovePersonalizzazioni);
		//Replace e confronto risultati
		giocoDellOca.replaceAllPersonalizzazioni(nuovePersonalizzazioni);
		assertEquals(nuovePersonalizzazioni.size(), giocoDellOca.getListaPersonalizzazioni().size());
		assertNotEquals(oldPersonalizzazioni, giocoDellOca.getListaPersonalizzazioni().size());
	}
}
