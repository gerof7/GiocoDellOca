package GiocoDellOca;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
/**
*
* @author Francesco
*/
public class Partita {
	
	private int idPartita;
	private LocalDateTime dataOraInizio;
	
	private Impostazioni impostazioni;
	
	private Map<String, Regola> elencoRegole;
	private Map<String, Personalizzazione> elencoPersonalizzazioni;
	private Map<String, Scenario> elencoScenari;
	
	public Partita() {
        this.elencoRegole = new HashMap<>();
        this.elencoPersonalizzazioni = new HashMap<>();
        this.elencoScenari = new HashMap<>();
        
        loadRegole();
        loadScenari();
        loadPersonalizzazioni();
        
        impostazioni = new Impostazioni();
    }
	
	private void loadRegole() {
		//TO DO
		Regola regola = new Regola("codiceRegola", "descrizione", "proprieta");
		elencoRegole.put("1", regola);
		//...
	}
	
	private void loadPersonalizzazioni() {
		//TO DO
		Personalizzazione personalizzazione = new Personalizzazione("codicePersonalizzazione", "descrizione");
		elencoPersonalizzazioni.put("1", personalizzazione);
		//...
	}
	
	private void loadScenari() {
		//TO DO
		Scenario scenario = new Scenario("codiceScenario", "descrizione");
		elencoScenari.put("1", scenario);
		//...
	}
	
	
	
	
	public Map<String, Regola> getRegole(){
		return elencoRegole;
	}
	
	public Map<String, Personalizzazione> getPersonalizzazioni(){
		return elencoPersonalizzazioni;
	}
	
	public Map<String, Scenario> getScenari(){
		return elencoScenari;
	}
	
}
