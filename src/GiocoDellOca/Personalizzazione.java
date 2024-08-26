package GiocoDellOca;

public class Personalizzazione {
	
	private String codicePersonalizzazione;
	private String descrizione;
	private String path;
	
	public Personalizzazione(String codicePersonalizzazione, String descrizione, String path) {
		
		this.codicePersonalizzazione = codicePersonalizzazione;
		this.descrizione = descrizione;
		this.path = path;
	}
	
	public Personalizzazione() {
		this.codicePersonalizzazione = "Default";
		this.descrizione = "Default";
	}

	public String getCodicePersonalizzazione() {
		return codicePersonalizzazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getPath() {
		return path;
	}
		
}
