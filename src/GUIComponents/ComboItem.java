package GUIComponents;

import java.util.Objects;

public class ComboItem {
    private String codice;
    private String descrizione;

    public ComboItem(String codice, String descrizione) {
        this.codice = codice;
        this.descrizione = descrizione;
    }

    public String getCodice() {
        return codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return descrizione;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ComboItem other = (ComboItem) obj;
        return codice.equals(other.codice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codice);
    }
    
}

