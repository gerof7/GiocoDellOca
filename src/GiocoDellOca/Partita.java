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
	
	public Partita(Giocatore giocatore) {      
        this.impostazioni = new Impostazioni();
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
	
	public void impostaPartitaSP() {
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
	
	public void aggiungiGiocatoriOspiti(int numeroTotaleGiocatori) {
	    for (int i = 2; i <= numeroTotaleGiocatori; i++) {
	        Giocatore giocatoreOspite = new Giocatore("Giocatore " + i, i);
	        giocatori.put(i, giocatoreOspite);
	    }
	}
	
}
