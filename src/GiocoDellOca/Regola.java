package GiocoDellOca;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regola regola = (Regola) o;
        return (Objects.equals(codiceRegola, regola.codiceRegola) && Objects.equals(descrizione, regola.descrizione) && Objects.equals(proprietaRegola, regola.proprietaRegola));
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiceRegola, descrizione, proprietaRegola);
    }
	
	
	
}
