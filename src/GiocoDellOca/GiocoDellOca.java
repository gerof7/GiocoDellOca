package GiocoDellOca;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
*
* @author Francesco
*/
public class GiocoDellOca {
	
	private static GiocoDellOca giocoDellOca;
	
	private Giocatore giocatoreInSessione;
	private Partita partitaCorrente;
	
	private Map<String, Regola> mappaRegole;
	private Map<String, Personalizzazione> mappaPersonalizzazioni;
	private Map<String, Scenario> mappaScenari;
	
	public GiocoDellOca() {
		
		this.mappaRegole = new HashMap<>();
        this.mappaPersonalizzazioni = new HashMap<>();
        this.mappaScenari = new HashMap<>();
        
        loadRegole();
        loadScenari();
        loadPersonalizzazioni();
        
        avviaPartita(1);
	}
	
	public void loadRegole() {
		//TO DO
		Regola regola = new Regola("codiceRegola", "descrizione", "proprieta");
		mappaRegole.put("1", regola);
		//...
	}
	
	public void loadPersonalizzazioni() {
		//TO DO
		Personalizzazione personalizzazione = new Personalizzazione("codicePersonalizzazione", "descrizione");
		mappaPersonalizzazioni.put("1", personalizzazione);
		//...
	}
	
	public void loadScenari() {
		//TO DO
		Scenario scenario = new Scenario("codiceScenario", "descrizione");
		mappaScenari.put("1", scenario);
		//...
	}
	
	public void avviaPartita(int idPartita) {
		
		System.out.println("Welcome, starting Partita");
		partitaCorrente = new Partita(idPartita);
		
		System.out.println("Caricamento delle regole");
		partitaCorrente.editImpostazioni().getRegole(mappaRegole);
		
		System.out.println("Caricamento delle personalizzazioni");
		partitaCorrente.editImpostazioni().getPersonalizzazioni(mappaPersonalizzazioni);
		
		System.out.println("Caricamento degli scenari");
		partitaCorrente.editImpostazioni().getScenari(mappaScenari);
	}
	
	public Map<String, Regola> getRegole(){
		return mappaRegole;
	}
	
	public Map<String, Personalizzazione> getPersonalizzazioni(){
		return mappaPersonalizzazioni;
	}
	
	public Map<String, Scenario> getScenari(){
		return mappaScenari;
	}
	
}
