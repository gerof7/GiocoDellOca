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
	
	private Map<String, Regola> mappaRegole;
	private Map<String, Personalizzazione> mappaPersonalizzazioni;
	private Map<String, Scenario> mappaScenari;
	
	private Scenario scenario;
	
	private TipologiaRegoleEnum tipologiaRegole;
	private TipologiaPersonalizzazioneEnum tipologiaPersonalizzazione;
	
	
	public Impostazioni() {
		this.mappaRegole = new HashMap<>();
        this.mappaPersonalizzazioni = new HashMap<>();
        this.mappaScenari = new HashMap<>();
	}
	
	
	
	public void setTipologiaRegole(TipologiaRegoleEnum selectedTipologiaRegole ) {
		tipologiaRegole = selectedTipologiaRegole;
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
	
	public void setTipologiaPersonalizzazione(TipologiaPersonalizzazioneEnum selectedTipologiaPersonalizzazione ) {
		tipologiaPersonalizzazione = selectedTipologiaPersonalizzazione;
	}
	
	public void addPersonalizzazioneToList(Personalizzazione personalizzazione) {
		elencoPersonalizzazioni.add(personalizzazione);
	}
	
	public void getRegole(Map<String, Regola> regole){
		mappaRegole = regole;
	}
	
	public void getPersonalizzazioni(Map<String, Personalizzazione> personalizzazioni){
		mappaPersonalizzazioni = personalizzazioni;
	}
	
	public void getScenari(Map<String, Scenario> scenari){
		mappaScenari = scenari;
	}
	
	
	
	
}
