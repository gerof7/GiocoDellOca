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
	
	
	
	public Partita(int idPartita) {
        
        impostazioni = new Impostazioni();
        
    }
	
	public Impostazioni editImpostazioni() {
		return impostazioni;
	}
	
	
	
	
	
	
}
