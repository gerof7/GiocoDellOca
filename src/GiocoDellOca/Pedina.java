package GiocoDellOca;

public class Pedina extends Personalizzazione{

	private int stato;
	private int posizione;
	
	public Pedina(String codicePersonalizzazione, String descrizione, String path) {
		super(codicePersonalizzazione, descrizione, path);
		this.posizione = 1;
	}

	public Pedina() {
		super();
		this.posizione = 1;
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
		
	public void Muovi(int spostamento, int maxPosizione) {
		posizione += spostamento;
        if (posizione > maxPosizione) {
        	posizione = maxPosizione;
        } else if (posizione < 1) {
        	posizione = 1;
        }
    }
	
}
