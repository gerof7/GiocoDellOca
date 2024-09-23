package GiocoDellOca;

public class CasellaSpeciale extends Casella{

	private TipologiaCasellaSpecialeEnum tipologiaCasellaSpeciale;
	
	public CasellaSpeciale(int numero, String descrizione, TipologiaCasellaSpecialeEnum tipologiaCasellaSpeciale) {
		super(numero, descrizione);
		this.tipologiaCasellaSpeciale = tipologiaCasellaSpeciale;	
	}

	public TipologiaCasellaSpecialeEnum getTipologiaCasellaSpeciale() {
		return tipologiaCasellaSpeciale;
	}

}
