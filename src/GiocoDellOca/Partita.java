package GiocoDellOca;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
/**
*
* @author Francesco
*/
public class Partita {
	
	private int idPartita;
	private LocalDateTime dataOraInizio;
	
	private Impostazioni impostazioni;
	
	
	
	public Partita(int idPartitaAssegnato) {
        
		idPartita = idPartitaAssegnato;
        impostazioni = new Impostazioni();
        
    }
	
	public Impostazioni editImpostazioni() {
		return impostazioni;
	}
	
	public int getId() {
		return idPartita;
	}
	
	
	
	
	
	
}
