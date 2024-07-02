package GiocoDellOca;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
/**
*
* @author Francesco
*/
public class Partita {
	
	
	private Impostazioni impostazioni;
	
	public Partita() {
        
        impostazioni = new Impostazioni();
        
    }
	
	public Impostazioni getImpostazioni() {
		return impostazioni;
	}
	
}
