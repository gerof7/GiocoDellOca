package GiocoDellOca;

import java.util.Random;

public class Dado extends Personalizzazione{
	
	public Dado(String codicePersonalizzazione, String descrizione, String path) {
		super(codicePersonalizzazione, descrizione, path);
	}
	
	public Dado() {
		super();
	}

	private int valoreFaccia;

	public int getValoreFaccia() {
		return valoreFaccia;
	}

	public void lanciaDado() {
		Random random = new Random();
		this.valoreFaccia = random.nextInt(6) + 1;
 	}
	
}
