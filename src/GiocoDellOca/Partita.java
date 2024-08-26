package GiocoDellOca;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
/**
*
* @author Francesco
*/
public class Partita {
	
	private Impostazioni impostazioni;
	private Tabellone tabellone;
	private Giocatore giocatoreInSessione;
	
	public Partita(Giocatore giocatore) {      
        this.impostazioni = new Impostazioni();
        this.giocatoreInSessione = giocatore;
    }
	
	public Impostazioni getImpostazioni() {
		return impostazioni;
	}

	public Tabellone getTabellone() {
		return tabellone;
	}

	public void setTabellone(Tabellone tabellone) {
		this.tabellone = tabellone;
	}
	
	public void impostaPartitaSP() {
		var regole = impostazioni.getElencoRegole();
		var scenario = impostazioni.getScenario();
		var personalizzazioni = impostazioni.getElencoPersonalizzazioni();
		var dado = new Dado();
		var pedina = new Pedina();
		
		
		for(var personalizzazione : personalizzazioni){
			if(personalizzazione instanceof Dado)
				dado = (Dado) personalizzazione;
			else if ( personalizzazione instanceof Pedina)
				pedina = (Pedina) personalizzazione;
		}
		
		this.tabellone = new Tabellone(regole, scenario);
		this.giocatoreInSessione.setDado(dado);
		this.giocatoreInSessione.setPedina(pedina);
	}
	
}
