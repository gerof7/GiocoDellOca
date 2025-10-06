package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
		 var giocatoreHost = new Giocatore("Giocatore 1", 1);
		 var partita = new Partita(giocatoreHost);
		 
		 partita.aggiungiGiocatoriOspiti(4);
		 
		 assertEquals(partita.getAllGiocatori().size(), 4);		

	 }

}
