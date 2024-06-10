package GiocoDellOca;

import java.util.List;

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
	
	
	
	
}
