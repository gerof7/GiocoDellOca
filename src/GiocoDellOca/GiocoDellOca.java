package GiocoDellOca;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
*
* @author Francesco
*/
public class GiocoDellOca {
	
	private static GiocoDellOca giocoDellOca;
	private boolean faseImpostazioni;
	
	private Giocatore giocatoreInSessione;
	private Partita partitaCorrente;
	
	public Partita avviaPartita() {
		
		partitaCorrente = new Partita();
		
		return partitaCorrente;
	}
}
