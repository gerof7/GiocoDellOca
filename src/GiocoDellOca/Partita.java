package GiocoDellOca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
*
* @author Francesco
*/
public class Partita {
	
	private Impostazioni impostazioni;
	private Tabellone tabellone;
	private Map<Integer, Giocatore> giocatori = new HashMap<>();
	
	public Partita(String nomeGiocatoreInSessione) {      
        this.impostazioni = new Impostazioni();
        
        var giocatore = new Giocatore(nomeGiocatoreInSessione, 1);
        this.giocatori.put(giocatore.getNumero(), giocatore);
    }
	
	public Impostazioni getImpostazioni() {
		return impostazioni;
	}

	public Tabellone getTabellone() {
		return tabellone;
	}

	public Giocatore getGiocatoreInSessione() {
		return giocatori.get(1);
	}
	
	public Giocatore getGiocatore(int numero) {
	    return giocatori.get(numero);
	}

	public void setTabellone(Tabellone tabellone) {
		this.tabellone = tabellone;
	}
	
	public Map<Integer, Giocatore> getAllGiocatori() {
		return giocatori;
	}

	public void impostaPartita() {
		var regole = impostazioni.getElencoRegole();
		var scenario = impostazioni.getScenario();
		var personalizzazioni = impostazioni.getElencoPersonalizzazioni();
		var dado = new Dado("Dado_Classico", "Il dado del giocatore è il dado classico", "./src/images/dadoclassico_1.png");
		var pedina = new Pedina("Pedina_Oca", "La pedina del giocatore è un'oca", "./src/images/ScarfGoose.png");
		
		for(var personalizzazione : personalizzazioni){
			if(personalizzazione instanceof Dado)
				dado = (Dado) personalizzazione;
			else if ( personalizzazione instanceof Pedina)
				pedina = (Pedina) personalizzazione;
		}
		
		this.tabellone = new Tabellone(regole, scenario);
		Giocatore giocatoreInSessione = this.getGiocatoreInSessione();
		giocatoreInSessione.setDado(dado);
		giocatoreInSessione.setPedina(pedina);
	}
	
	public void aggiungiGiocatoriOspiti(int numeroTotaleGiocatori, Map<Integer, String> nomiGiocatoriDefault) {
	    for (int i = 2; i <= numeroTotaleGiocatori; i++) {    	
	    	var nomeGiocatoreDefault = nomiGiocatoriDefault.get(i);
	    	var nomeGiocatore = nomeGiocatoreDefault != null && !nomeGiocatoreDefault.isBlank()  ? nomeGiocatoreDefault : "Giocatore " + i;
	    	
	        Giocatore giocatoreOspite = new Giocatore(nomeGiocatore, i);
	        giocatori.put(i, giocatoreOspite);
	    }
	}
	
	public void impostaRegoleSingole(List<Regola> listaRegoleSingole, javax.swing.table.TableModel tableModel) {
	    impostazioni.impostaRegoleSingole(listaRegoleSingole, tableModel);
	}

	public void impostaRegoleDaSet(Map<String, Set<Regola>> mapRegoleSet, javax.swing.table.TableModel tableModel) {
	    impostazioni.impostaRegoleDaSet(mapRegoleSet, tableModel);
	}
	
	public void impostaScenario(String codiceScenario, List<Scenario> listaScenari) {
	        this.impostazioni.impostaScenarioDaCodice(codiceScenario, listaScenari);	   
	}
	
	private Pedina creaPedinaDefault() {
	    return new Pedina("Pedina_Oca", "La pedina del giocatore è un'oca", "./src/images/ScarfGoose.png");
	}

	private Dado creaDadoDefault() {
	    return new Dado("Dado_Classico", "Il dado del giocatore è il dado classico", "./src/images/dadoclassico_1.png");
	}

	public void impostaPedinaGiocatore(String codicePedina, int numeroGiocatore, List<Personalizzazione> personalizzazioni, boolean isMultiplayer) {
	    if (isMultiplayer) {
	        Giocatore giocatore = getGiocatore(numeroGiocatore);
	        boolean trovata = false;

	        for (var p : personalizzazioni) {
	            if (p instanceof Pedina && p.getCodicePersonalizzazione().equals(codicePedina)) {
	                giocatore.setPedina((Pedina) p);
	                trovata = true;
	                break;
	            }
	        }

	        if (!trovata || giocatore.getPedina() == null) {
	            giocatore.setPedina(creaPedinaDefault());
	        }

	    } else {
	        boolean trovata = false;
	        for (var p : personalizzazioni) {
	            if (p instanceof Pedina && p.getCodicePersonalizzazione().equals(codicePedina)) {
	                this.impostazioni.addPersonalizzazioneToList(p);
	                trovata = true;
	                break;
	            }
	        }

	        if (!trovata) {
	            this.impostazioni.addPersonalizzazioneToList(creaPedinaDefault());
	        }
	    }
	}

	public void impostaDadoGiocatore(String codiceDado, int numeroGiocatore, List<Personalizzazione> personalizzazioni, boolean isMultiplayer) {
	    if (isMultiplayer) {
	        Giocatore giocatore = getGiocatore(numeroGiocatore);
	        boolean trovato = false;

	        for (var p : personalizzazioni) {
	            if (p instanceof Dado && p.getCodicePersonalizzazione().equals(codiceDado)) {
	                giocatore.setDado((Dado) p);
	                trovato = true;
	                break;
	            }
	        }

	        if (!trovato || giocatore.getDado() == null) {
	            giocatore.setDado(creaDadoDefault());
	        }

	    } else {
	        boolean trovato = false;
	        for (var p : personalizzazioni) {
	            if (p instanceof Dado && p.getCodicePersonalizzazione().equals(codiceDado)) {
	                this.impostazioni.addPersonalizzazioneToList(p);
	                trovato = true;
	                break;
	            }
	        }

	        if (!trovato) {
	            this.impostazioni.addPersonalizzazioneToList(creaDadoDefault());
	        }
	    }
	}


	
}
