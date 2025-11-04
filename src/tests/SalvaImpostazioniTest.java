package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GiocoDellOca.GiocoDellOca;

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
	

}
