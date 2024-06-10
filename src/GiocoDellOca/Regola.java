package GiocoDellOca;

import java.time.LocalDate;
import java.util.HashMap;

public class Regola {
	
	private String codiceRegola;
	private String descrizione;
	private String proprietaRegola;
	
	public Regola(String codiceRegola, String descrizione, String proprietaRegola) {
        this.codiceRegola = codiceRegola;
        this.descrizione = descrizione;
        this.proprietaRegola = proprietaRegola;
    }
	
}
