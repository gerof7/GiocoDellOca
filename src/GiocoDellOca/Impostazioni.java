package GiocoDellOca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* @author Francesco
*/
public class Impostazioni {

	private List<Regola> elencoRegole;
	private List<Personalizzazione> elencoPersonalizzazioni;
	
	private Scenario scenario;
	
	private TipologiaRegoleEnum tipologiaRegole;
	private TipologiaPersonalizzazioneEnum tipologiaPersonalizzazione;
	
	
	public Impostazioni() {
	}
	
	
	
	public void setTipologiaRegole(TipologiaRegoleEnum selectedTipologiaRegole ) {
		tipologiaRegole = selectedTipologiaRegole;
	}
	
	public TipologiaRegoleEnum getTipologiaRegole() {
		return tipologiaRegole;
	}
	
	public void addRegolaToList(Regola regola) {
		elencoRegole.add(regola);
	}
	
	public void setListaRegole(List<Regola> listaRegole) {
		elencoRegole = listaRegole;
	}
	
	public void setScenario(Scenario selectedScenario) {
		scenario = selectedScenario;
	}
	public Scenario getScenario() {
		return scenario;
	}
	
	public void setTipologiaPersonalizzazione(TipologiaPersonalizzazioneEnum selectedTipologiaPersonalizzazione ) {
		tipologiaPersonalizzazione = selectedTipologiaPersonalizzazione;
	}
	
	public TipologiaPersonalizzazioneEnum getTipologiaPersonalizzazione() {
		return tipologiaPersonalizzazione;
	}
	
	public void addPersonalizzazioneToList(Personalizzazione personalizzazione) {
		elencoPersonalizzazioni.add(personalizzazione);
	}
	
	
	
	
}
