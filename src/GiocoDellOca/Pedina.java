package GiocoDellOca;

public class Pedina extends Personalizzazione{

	private int stato;
	private int posizione;
	
	public Pedina(String codicePersonalizzazione, String descrizione, String path) {
		super(codicePersonalizzazione, descrizione, path);
	}

	public Pedina() {
		super();
	}

	public int getStato() {
		return stato;
	}
	
	public void setStato(int stato) {
		this.stato = stato;
	}
	public int getPosizione() {
		return posizione;
	}
	
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
		
	
}
