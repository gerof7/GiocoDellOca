package GiocoDellOca;

public class CasellaSpeciale extends Casella{

	private int modificatore;
	private int malus;
	
	public CasellaSpeciale(int numero, String descrizione, int modificatore, int malus) {
		super(numero, descrizione);
		this.modificatore = modificatore;
		this.malus = malus;
	}

	public int getModificatore() {
		return modificatore;
	}

	public int getMalus() {
		return malus;
	}

}
