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
		Regola regola1 = new Regola("numero_caselle", "Seleziona il numero delle caselle di cui sarà composto il tabellone di gioco", "", TipologiaRegolaEnum.NumeroCaselle);
		Regola regola2 = new Regola("numero_dadi", "Seleziona il numero di dadi con cui vuoi giocare","", TipologiaRegolaEnum.NumeroDadi);
		listaRegoleSingole.add(regola1);
		listaRegoleSingole.add(regola2);
	}
	
	public void loadRegoleSet() {
		Regola regola1 = new Regola("regola1_partita_breve","Numero caselle", "42", TipologiaRegolaEnum.NumeroCaselle);
		Regola regola2 = new Regola("regola2_partita_breve", "Numero dadi", "3", TipologiaRegolaEnum.NumeroDadi);
		Regola regola3 = new Regola("regola1_partita_classica", "Numero caselle", "63", TipologiaRegolaEnum.NumeroCaselle);
		Regola regola4 = new Regola("regola2_partita_classica", "Numero dadi", "2", TipologiaRegolaEnum.NumeroDadi);
		
		Set<Regola> regolaSet1 = new LinkedHashSet<>();
		Set<Regola> regolaSet2 = new LinkedHashSet<>();
		
		regolaSet1.add(regola1);
		regolaSet1.add(regola2);
		regolaSet2.add(regola3);
		regolaSet2.add(regola4);	
		
		mapRegoleSet.put("Partita breve", regolaSet1);
		mapRegoleSet.put("Partita classica", regolaSet2);		
	}
	
	
	public void loadPersonalizzazioni() {
		Personalizzazione personalizzazione1 = new Dado("Dado_Classico", "Il dado del giocatore è il dado classico", "./src/images/dadoclassico_1.png");
		Personalizzazione personalizzazione3 = new Dado("Dado_Rosso", "Il dado del giocatore è un dado nero e rosso", "./src/images/dadorosso_1.png");
		Personalizzazione personalizzazione2 = new Pedina("Pedina_Oca", "La pedina del giocatore è un'oca", "./src/images/ScarfGoose.png");
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
	
	public void configuraNuovaPartita() {
		
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
	
	
	public void avviaPartita(boolean isMultiplayer) {		
		if(!isMultiplayer)
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
	
	public void resetPartita() {
	    this.partitaCorrente = null;
	}

}
