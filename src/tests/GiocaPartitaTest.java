package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import GiocoDellOca.Giocatore;
import GiocoDellOca.Partita;
import GiocoDellOca.Pedina;
import GiocoDellOca.Regola;
import GiocoDellOca.Scenario;
import GiocoDellOca.Tabellone;
import GiocoDellOca.TipologiaRegolaEnum;

class GiocaPartitaTest {

	@Test
	void pedinaTest() {
		var pedina = new Pedina();
		pedina.Muovi(4, 63);
		assertEquals(pedina.getPosizione(), 5);
		pedina.Muovi(59, 63);
		assertEquals(pedina.getPosizione(), 62);		
	}
	
	@Test
	void tabelloneTest() {
		Regola regola1 = new Regola("numero_caselle", "Seleziona il numero delle caselle di cui sarà composto il tabellone di gioco", "63", TipologiaRegolaEnum.NumeroCaselle);
		Regola regola2 = new Regola("numero_dadi", "Seleziona il numero di dadi con cui vuoi giocare","2", TipologiaRegolaEnum.NumeroDadi);
		List<Regola> regole = new ArrayList<Regola>();
		regole.add(regola1);
		regole.add(regola2);
		var scenario = new Scenario("SCN-TEST", "Scenario Test", "", "", "", "", "", "");
		
		var tabellone = new Tabellone(regole, scenario);
		assertNotNull(tabellone);	
	}
	
	 @Test
	 void partitaTest() {
		 var nomeGiocatoreHost = "Giocatore 1";
		 var partita = new Partita(nomeGiocatoreHost);
		 
		 Map<Integer, String> nomiGiocatoriDefault = new LinkedHashMap<>();
		 nomiGiocatoriDefault.put(1, "Nome 1");
		 nomiGiocatoriDefault.put(2, "Nome 2");
		 nomiGiocatoriDefault.put(3, "Nome 3");
		 nomiGiocatoriDefault.put(4, "Nome 4");
		 
		 
		 partita.aggiungiGiocatoriOspiti(4, nomiGiocatoriDefault);
		 
		 assertEquals(partita.getAllGiocatori().size(), 4);		

	 }

}
