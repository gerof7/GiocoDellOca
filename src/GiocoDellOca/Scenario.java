package GiocoDellOca;
/**
*
* @author Francesco
*/
public class Scenario {
	
	private String codiceScenario;
	private String descrizione;
	
	public Scenario(String codiceScenario, String descrizione) {
		
		this.codiceScenario = codiceScenario;
		this.descrizione = descrizione;
		
	}

	public String getCodiceScenario() {
		return codiceScenario;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
}
