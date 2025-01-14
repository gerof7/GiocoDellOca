package GiocoDellOca;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author Francesco
*/
public class Impostazioni {

	private List<Regola> elencoRegole;
	private List<Personalizzazione> elencoPersonalizzazioni;
	private Scenario scenario;
		
	public Impostazioni() {
		this.elencoRegole = new ArrayList<Regola>();
		this.elencoPersonalizzazioni = new ArrayList<Personalizzazione>();
	}	
	
	public void addRegolaToList(Regola regola) {
		elencoRegole.add(regola);
	}
	
	public void setScenario(Scenario selectedScenario) {
		scenario = selectedScenario;
	}
	
	public Scenario getScenario() {
		return scenario;
	}
		
	public void addPersonalizzazioneToList(Personalizzazione personalizzazione) {
		elencoPersonalizzazioni.add(personalizzazione);
	}

	public List<Regola> getElencoRegole() {
		return elencoRegole;
	}
	public List<Personalizzazione> getElencoPersonalizzazioni() {
		return elencoPersonalizzazioni;
	}
}
