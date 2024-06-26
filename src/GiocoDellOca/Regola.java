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

	public String getProprietaRegola() {
		return proprietaRegola;
	}

	public void setProprietaRegola(String proprietaRegola) {
		this.proprietaRegola = proprietaRegola;
	}

	public String getCodiceRegola() {
		return codiceRegola;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
	
	
}
