package GiocoDellOca;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        
        this.giocatoreInSessione = new Giocatore("Giocatore 1", 1);
        
        this.partitaCorrente = configuraNuovaPartitaSP();
        avviaPartita();
	}
	
	public void loadRegole() {
		//TO DO
		//Regola regola1 = new Regola("codiceRegola", "descrizione", "proprieta");
		Regola regola1 = new Regola("Partita_normale", "Nessun tipo di vantaggio o svantaggio aggiunto", "Normale");
		Regola regola2 = new Regola("Partita_lunga", "Si gioca col numero doppio di caselle e imprevisti", "Media");
		Regola regola3 = new Regola("Partita_breve", "Si gioca col numero dimezzato di caselle e imprevisti", "Facile");
		Regola regola4 = new Regola("Partita_difficile", "Non si finisce se non si esegue un numero doppio coi dadi", "Difficile");
		mappaRegole.put("1", regola1);
		mappaRegole.put("2", regola2);
		mappaRegole.put("3", regola3);
		mappaRegole.put("4", regola4);
		for (Map.Entry<String,Regola> entry : mappaRegole.entrySet()) {
			System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue());
		}
		//...
	}
	
	public void loadPersonalizzazioni() {
		//TO DO
		//Personalizzazione personalizzazione = new Personalizzazione("codicePersonalizzazione", "descrizione");
		Personalizzazione personalizzazione1 = new Personalizzazione("Dado_doppio", "Due dadi");
		Personalizzazione personalizzazione2 = new Personalizzazione("Dado_triplo", "Tre dadi");
		Personalizzazione personalizzazione3 = new Personalizzazione("Pedina_Oca", "Il personaggio giocatore è un'oca");
		mappaPersonalizzazioni.put("1", personalizzazione1);
		mappaPersonalizzazioni.put("2", personalizzazione2);
		mappaPersonalizzazioni.put("3", personalizzazione3);
		
		for (Map.Entry<String,Personalizzazione> entry : mappaPersonalizzazioni.entrySet()) {
			System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue());
		}
            
		
		//...
	}
	
	public void loadScenari() {
		//TO DO
		//Scenario scenario = new Scenario("codiceScenario", "descrizione");
		Scenario scenario1 = new Scenario("Fiabe", "Ogni casella ha una fiaba diversa");
		Scenario scenario2 = new Scenario("Favole", "Ogni casella ha una favola diversa");
		Scenario scenario3 = new Scenario("Anime", "Ogni casella rappresenta un anime diverso");
		Scenario scenario4 = new Scenario("Citta", "Ogni casella rappresenta un posto tipico di una città");
		mappaScenari.put("1", scenario1);
		mappaScenari.put("2", scenario2);
		mappaScenari.put("3", scenario3);
		mappaScenari.put("4", scenario4);
		for (Map.Entry<String,Scenario> entry : mappaScenari.entrySet()) {
			System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue());
		}
		//...
	}
	
	public Partita configuraNuovaPartitaSP() {
		
		System.out.println("Configurazione Partita");
		var idPartita = new Random().nextInt(100);
		var partita = new Partita(idPartita);
		
		System.out.println("Caricamento delle regole");
		loadRegole();
//		partitaCorrente.editImpostazioni().setTipologiaRegole(TipologiaRegoleEnum.RegolaSingola);
//		if (partitaCorrente.editImpostazioni().getTipologiaRegole() == TipologiaRegoleEnum.RegolaSingola) {
//			//TODO
//			System.out.println("Aggiunta regole singole");
//		}
//		else {
//			//TODO
//			System.out.println("Aggiunta della lista regole");
//		}
		
		System.out.println("Caricamento delle personalizzazioni");
		loadPersonalizzazioni();
//		partitaCorrente.editImpostazioni().setTipologiaPersonalizzazione(TipologiaPersonalizzazioneEnum.Dado);
//		if (partitaCorrente.editImpostazioni().getTipologiaPersonalizzazione() == TipologiaPersonalizzazioneEnum.Dado) {
//			//TODO
//			System.out.println("Aggiunta personalizzazione dado");
//		}
//		else {
//			//TODO
//			System.out.println("Aggiunta personalizzazione pedina");
//		}
		
		System.out.println("Caricamento degli scenari");
		loadScenari();
//		partitaCorrente.editImpostazioni().setScenario(mappaScenari.get("codiceScenario"));
//		System.out.println("Aggiunto scenario");
		
		return partita;
	}
	
	
	public void avviaPartita() {
		
		System.out.println("Welcome, starting Partita");
		
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
