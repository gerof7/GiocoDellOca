package GiocoDellOca;

import javax.swing.JOptionPane;

public class Pedina extends Personalizzazione{

	private int stato;
	private int posizione;
	
	public Pedina(String codicePersonalizzazione, String descrizione, String path) {
		super(codicePersonalizzazione, descrizione, path);
		this.posizione = 1;
		this.stato = 1;
	}

	public Pedina() {
		super();
		this.posizione = 1;
		this.stato = 1;
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
		
	public int Muovi(int spostamento, int maxPosizione) {
		int numeroCaselleRimbalzoIndietro = 0;
		
	    posizione += spostamento;

	    // Se supera la casella finale, calcola il rimbalzo
	    if (posizione > maxPosizione) {
	        int differenza = posizione - maxPosizione;
	        posizione = maxPosizione - differenza; // Rimbalza all'indietro

	        numeroCaselleRimbalzoIndietro = differenza;
	        
	    } else if (posizione < 1) {
	        posizione = 1; // Evita di andare sotto la prima casella
	    }
	    
	    return numeroCaselleRimbalzoIndietro;
	}

	
}
