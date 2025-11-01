package GiocoDellOca;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public void impostaScenarioDaCodice(String codiceScenario, List<Scenario> listaScenari) {
	    for (var s : listaScenari) {
	        if (s.getCodiceScenario().equals(codiceScenario)) {
	            this.scenario = s;
	            return;
	        }
	    }

	    this.scenario = new Scenario(
	        "SCN_DEFAULT",
	        "Scenario classico",
	        "Casella dell'oca classica",
	        "Casella del ponte classica",
	        "Casella della locanda classica",
	        "Casella della prigione classica",
	        "Casella del labirinto classica",
	        "Casella dello scheletro classica"
	    );
	}
	
	public void impostaRegoleSingole(List<Regola> listaRegoleSingole, javax.swing.table.TableModel tableModel) {
	    this.elencoRegole.clear();
	    
	    if (tableModel == null) {
	        elencoRegole.addAll(listaRegoleSingole);
	        return;
	    }

	    for (int row = 0; row < tableModel.getRowCount(); row++) {
	        String codiceRegola = (String) tableModel.getValueAt(row, 0);
	        String descrizioneRegola = (String) tableModel.getValueAt(row, 1);
	        String proprietaRegola = (String) tableModel.getValueAt(row, 2);

	        TipologiaRegolaEnum tipologia = null;

	        for (var r : listaRegoleSingole) {
	            if (r.getCodiceRegola().equals(codiceRegola)) {
	                tipologia = r.getTipologiaRegola();
	                break;
	            }
	        }

	        this.elencoRegole.add(new Regola(codiceRegola, descrizioneRegola, proprietaRegola, tipologia));
	    }
	}
	
	public void impostaRegoleDaSet(Map<String, Set<Regola>> mapRegoleSet, javax.swing.table.TableModel tableModel) {
	    this.elencoRegole.clear();
	    
	    if (tableModel == null) {
	        if (!mapRegoleSet.isEmpty()) {
	            var primoSet = mapRegoleSet.values().iterator().next();
	            elencoRegole.addAll(primoSet);
	        }
	        return;
	    }

	    for (int row = 0; row < tableModel.getRowCount(); row++) {
	        String codiceRegola = (String) tableModel.getValueAt(row, 0);
	        String descrizioneRegola = (String) tableModel.getValueAt(row, 1);
	        String proprietaRegola = (String) tableModel.getValueAt(row, 2);

	        TipologiaRegolaEnum tipologia = null;

	        outerLoop:
	        for (var entry : mapRegoleSet.entrySet()) {
	            for (var value : entry.getValue()) {
	                if (value.getCodiceRegola().equals(codiceRegola)) {
	                    tipologia = value.getTipologiaRegola();
	                    break outerLoop;
	                }
	            }
	        }

	        this.elencoRegole.add(new Regola(codiceRegola, descrizioneRegola, proprietaRegola, tipologia));
	    }
	}
	
	public void impostaPedinaSelezionata(String codicePedina, List<Personalizzazione> personalizzazioni) {
	    for (var p : personalizzazioni) {
	        if (p instanceof Pedina && p.getCodicePersonalizzazione().equals(codicePedina)) {
	            this.elencoPersonalizzazioni.add(p);
	            return;
	        }
	    }
	}

	public void impostaDadoSelezionato(String codiceDado, List<Personalizzazione> personalizzazioni) {
	    for (var p : personalizzazioni) {
	        if (p instanceof Dado && p.getCodicePersonalizzazione().equals(codiceDado)) {
	            this.elencoPersonalizzazioni.add(p);
	            return;
	        }
	    }
	}




}
