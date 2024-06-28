package GiocoDellOca;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
*
* @author Francesco
*/
public class GiocoDellOca {
	
	private static GiocoDellOca giocoDellOca;
	
	private Giocatore giocatoreInSessione;
	private Partita partitaCorrente;
	
	private List<Regola> listaRegoleSingole;
	private Map<String, Set<Regola>> mapRegoleSet;
	private List<Personalizzazione> listaPersonalizzazioni;
	private List<Scenario> listaScenari;
	
	public GiocoDellOca() {
		
		this.listaRegoleSingole = new ArrayList<>();
		this.mapRegoleSet = new LinkedHashMap<>();
        this.listaPersonalizzazioni = new ArrayList<>();
        this.listaScenari = new ArrayList<>();
        
        this.giocatoreInSessione = new Giocatore("Giocatore 1", 1);
        
        this.partitaCorrente = configuraNuovaPartitaSP();
        avviaPartita(partitaCorrente);
	}
	
	public void loadRegoleSingole() {
		Regola regola1 = new Regola("Partita_normale", "Nessun tipo di vantaggio o svantaggio aggiunto", "Normale");
		Regola regola2 = new Regola("Partita_lunga", "Si gioca col numero doppio di caselle e imprevisti", "Media");
		Regola regola3 = new Regola("Partita_breve", "Si gioca col numero dimezzato di caselle e imprevisti", "Facile");
		Regola regola4 = new Regola("Partita_difficile", "Non si finisce se non si esegue un numero doppio coi dadi", "Difficile");
		listaRegoleSingole.add(regola1);
		listaRegoleSingole.add(regola2);
		listaRegoleSingole.add(regola3);
		listaRegoleSingole.add(regola4);
	}
	
	public void loadRegoleSet() {
		Regola regola1 = new Regola("Regola_1", "Regola placeholder numero 1", "Valore placeholder 1");
		Regola regola2 = new Regola("Regola_2", "Regola placeholder numero 2", "Valore placeholder 2");
		Regola regola3 = new Regola("Regola_3", "Regola placeholder numero 3", "Valore placeholder 3");
		Regola regola4 = new Regola("Regola_4", "Regola placeholder numero 4", "Valore placeholder 4");
		Regola regola5 = new Regola("Regola_5", "Regola placeholder numero 5", "Valore placeholder 5");
		Regola regola6 = new Regola("Regola_6", "Regola placeholder numero 6", "Valore placeholder 6");
		
		Set<Regola> regolaSet1 = new LinkedHashSet<>();
		Set<Regola> regolaSet2 = new LinkedHashSet<>();
		
		regolaSet1.add(regola1);
		regolaSet1.add(regola2);
		regolaSet1.add(regola3);
		regolaSet2.add(regola4);
		regolaSet2.add(regola5);
		regolaSet2.add(regola6);	
		
		mapRegoleSet.put("Set regole 1", regolaSet1);
		mapRegoleSet.put("Set regole 2", regolaSet2);		
	}
	
	
	public void loadPersonalizzazioni() {
		Personalizzazione personalizzazione1 = new Personalizzazione("Dado_doppio", "Due dadi");
		Personalizzazione personalizzazione2 = new Personalizzazione("Dado_triplo", "Tre dadi");
		Personalizzazione personalizzazione3 = new Personalizzazione("Pedina_Oca", "Il personaggio giocatore è un'oca");
		listaPersonalizzazioni.add(personalizzazione1);
		listaPersonalizzazioni.add(personalizzazione2);
		listaPersonalizzazioni.add(personalizzazione3);
	}
	
	public void loadScenari() {
		Scenario scenario1 = new Scenario("Fiabe", "Ogni casella ha una fiaba diversa");
		Scenario scenario2 = new Scenario("Favole", "Ogni casella ha una favola diversa");
		Scenario scenario3 = new Scenario("Anime", "Ogni casella rappresenta un anime diverso");
		Scenario scenario4 = new Scenario("Citta", "Ogni casella rappresenta un posto tipico di una città");
		listaScenari.add(scenario1);
		listaScenari.add(scenario2);
		listaScenari.add(scenario3);
		listaScenari.add(scenario4);
	}
	
	public Partita configuraNuovaPartitaSP() {
		
		var partita = new Partita();
		
		loadRegoleSingole();	
		loadRegoleSet();
		loadPersonalizzazioni();
		loadScenari();
		
		return partita;
	}
	
	
	public void avviaPartita(Partita partitaCorrente) {
		
		System.out.println(partitaCorrente);
		
	}

	public List<Regola> getListaRegoleSingole() {
		return listaRegoleSingole;
	}
	
	public Map<String, Set<Regola>> getMapRegoleSet() {
		return mapRegoleSet;
	}

	public List<Personalizzazione> getListaPersonalizzazioni() {
		return listaPersonalizzazioni;
	}

	public List<Scenario> getListaScenari() {
		return listaScenari;
	}
	
}
