package GiocoDellOca;
/**
*
* @author Francesco
*/
public class Scenario {
	
	private String codiceScenario;
	private String descrizione;
	private String descrizioneCasellaOca;
	private String descrizioneCasellaPonte;
	private String descrizioneCasellaLocanda;
	private String descrizioneCasellaPrigione;
	private String descrizioneCasellaLabirinto;
	private String descrizioneCasellaScheletro;

	public Scenario(String codiceScenario, String descrizione, String descrizioneCasellaOca,
			String descrizioneCasellaPonte, String descrizioneCasellaLocanda, String descrizioneCasellaPrigione,
			String descrizioneCasellaLabirinto, String descrizioneCasellaScheletro) {
		this.codiceScenario = codiceScenario;
		this.descrizione = descrizione;
		this.descrizioneCasellaOca = descrizioneCasellaOca;
		this.descrizioneCasellaPonte = descrizioneCasellaPonte;
		this.descrizioneCasellaLocanda = descrizioneCasellaLocanda;
		this.descrizioneCasellaPrigione = descrizioneCasellaPrigione;
		this.descrizioneCasellaLabirinto = descrizioneCasellaLabirinto;
		this.descrizioneCasellaScheletro = descrizioneCasellaScheletro;
	}

	public String getCodiceScenario() {
		return codiceScenario;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getDescrizioneCasellaOca() {
		return descrizioneCasellaOca;
	}

	public String getDescrizioneCasellaPonte() {
		return descrizioneCasellaPonte;
	}

	public String getDescrizioneCasellaLocanda() {
		return descrizioneCasellaLocanda;
	}

	public String getDescrizioneCasellaPrigione() {
		return descrizioneCasellaPrigione;
	}

	public String getDescrizioneCasellaLabirinto() {
		return descrizioneCasellaLabirinto;
	}

	public String getDescrizioneCasellaScheletro() {
		return descrizioneCasellaScheletro;
	}
	
	
	
}
