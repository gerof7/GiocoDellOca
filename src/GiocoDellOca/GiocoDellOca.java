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
		Personalizzazione personalizzazione1 = new Dado("Dado_doppio", "Due dadi", "path");
		Personalizzazione personalizzazione2 = new Dado("Dado_triplo", "Tre dadi", "path");
		Personalizzazione personalizzazione3 = new Pedina("Pedina_Oca", "Il personaggio giocatore è un'oca", "path");
		listaPersonalizzazioni.add(personalizzazione1);
		listaPersonalizzazioni.add(personalizzazione2);
		listaPersonalizzazioni.add(personalizzazione3);
	}
	
	public void loadScenari() {
		Scenario scenario1 = new Scenario("cappuccetto_rosso", "Cappuccetto Rosso",
				"La torta della nonna ha un profumo delizioso! (Avanzi del totale che hai appena ottenuto dal lancio dei dadi)",
				"Le indicazioni della mamma ti guidano lungo il sentiero del bosco! (Avanzi di un numero pari alla casella in cui ti trovi)",
				"Il lupo ti ferma per parlare! (Perdi 1 turno)",
				"Il lupo si è pappato Cappuccetto Rosso e la nonna! (Resti imprigionato finchè un altro giocatore non arriva su questa casella)",
				"State indietro! Il cacciatore è arrivato! (Torni indietro di 3 caselle) ",
				"Cappuccetto Rosso si è persa nel bosco! (Torni alla casella iniziale)");
		Scenario scenario2 = new Scenario("cenerentola", "Cenerentola", 
				"Le scarpette di cristallo sono davvero magnifiche! (Avanzi del totale che hai appena ottenuto dal lancio dei dadi)", 
				"La zucca-carrozza ti porta al ballo! (Avanzi di un numero pari alla casella in cui ti trovi)",
				"Le sorelle malvagie non ti fanno uscire! (Perdi 1 turno)",
				"Devi restare a casa a fare le pulizie! (Resti imprigionato finchè un altro giocatore non arriva su questa casella)",
				"La strada per il ballo è bloccata, devi tornare indietro! (Torni indietro di 3 caselle)",
				"È mezzanotte! L'effetto della magia è finito! (Torni alla casella iniziale)");

		listaScenari.add(scenario1);
		listaScenari.add(scenario2);
	}
	
	public void configuraNuovaPartitaSP() {
		
		var partitaCorrente = new Partita(giocatoreInSessione);
		
		if(listaRegoleSingole.isEmpty())
			loadRegoleSingole();
		
		if(mapRegoleSet.isEmpty())
			loadRegoleSet();
		
		if(listaPersonalizzazioni.isEmpty())
			loadPersonalizzazioni();
		
		if(listaScenari.isEmpty())
			loadScenari();
		
		this.partitaCorrente = partitaCorrente;
	}
	
	
	public void avviaPartita() {		
		partitaCorrente.impostaPartitaSP();
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
	
	public Partita getPartitaCorrente() {
		return partitaCorrente;
	}

}
