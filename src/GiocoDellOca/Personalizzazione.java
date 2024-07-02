package GiocoDellOca;

public class Personalizzazione {
	
	private String codicePersonalizzazione;
	private String descrizione;
	private TipologiaPersonalizzazioneEnum tipologiaPersonalizzazione;
	
	public Personalizzazione(String codicePersonalizzazione, String descrizione, TipologiaPersonalizzazioneEnum tipologiaPersonalizzazione) {
		
		this.codicePersonalizzazione = codicePersonalizzazione;
		this.descrizione = descrizione;
		this.tipologiaPersonalizzazione = tipologiaPersonalizzazione;	
	}

	public String getCodicePersonalizzazione() {
		return codicePersonalizzazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public TipologiaPersonalizzazioneEnum getTipologiaPersonalizzazione() {
		return tipologiaPersonalizzazione;
	}
		
}
