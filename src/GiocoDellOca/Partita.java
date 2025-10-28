package GiocoDellOca;

import java.util.HashMap;
import java.util.Map;

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
	
}
