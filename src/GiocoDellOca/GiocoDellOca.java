package GiocoDellOca;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Utilities.MossaResult;


public class GiocoDellOca {
		
	private Partita partitaCorrente;
	
	private List<Regola> listaRegoleSingole;
	private Map<String, Set<Regola>> mapRegoleSet;
	private List<Personalizzazione> listaPersonalizzazioni;
	private List<Scenario> listaScenari;
	
	private Map<Integer, String> nomiGiocatoriDefault;
	private String codiceScenarioDefault;
	private String codiceRegoleSetDefault;
	private Map<Integer, String> codiciDadiGiocatoriDefault;
	private Map<Integer, String> codiciPedineGiocatoriDefault;
	
	
	public GiocoDellOca() {
		
		this.listaRegoleSingole = new ArrayList<>();
		this.mapRegoleSet = new LinkedHashMap<>();
        this.listaPersonalizzazioni = new ArrayList<>();
        this.listaScenari = new ArrayList<>();
        
        this.nomiGiocatoriDefault = new LinkedHashMap<>();
        this.codiciDadiGiocatoriDefault = new LinkedHashMap<>();
        this.codiciPedineGiocatoriDefault = new LinkedHashMap<>();
            
        if(listaRegoleSingole.isEmpty())
			loadRegoleSingole();
		
		if(mapRegoleSet.isEmpty())
			loadRegoleSet();
		
		if(listaPersonalizzazioni.isEmpty())
			loadPersonalizzazioni();
		
		if(listaScenari.isEmpty())
			loadScenari();   
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
		Personalizzazione personalizzazione2 = new Pedina("Pedina_Oca", "La pedina del giocatore è l'oca classica", "./src/images/ScarfGoose.png");
		Personalizzazione personalizzazione4 = new Pedina("Pedina_OcaDonald", "La pedina del giocatore è un'oca con le sembianze di Paperino", "./src/images/DonaldGoose.png");
		Personalizzazione personalizzazione5 = new Pedina("Pedina_OcaGangster", "La pedina del giocatore è un'oca gangster", "./src/images/GangsterGoose.png");
		Personalizzazione personalizzazione6 = new Pedina("Pedina_OcaLady", "La pedina del giocatore è un'oca femminile", "./src/images/LadyGoose.png");
		Personalizzazione personalizzazione7 = new Pedina("Pedina_OcaKratos", "La pedina del giocatore è un'oca con le sembianze di Kratos", "./src/images/KratosGoose.png");
		listaPersonalizzazioni.add(personalizzazione1);
		listaPersonalizzazioni.add(personalizzazione2);
		listaPersonalizzazioni.add(personalizzazione3);
		listaPersonalizzazioni.add(personalizzazione4);
		listaPersonalizzazioni.add(personalizzazione5);
		listaPersonalizzazioni.add(personalizzazione6);
		listaPersonalizzazioni.add(personalizzazione7);

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
		var nomeDefaultGiocatoreInSessione = this.nomiGiocatoriDefault.get(1);
		var nomeGiocatore = nomeDefaultGiocatoreInSessione != null && !nomeDefaultGiocatoreInSessione.isBlank() ? nomeDefaultGiocatoreInSessione : "Giocatore 1";
			
		var partitaCorrente = new Partita(nomeGiocatore);	
		this.partitaCorrente = partitaCorrente;
	}
	
	
	public void avviaPartita() {		
		partitaCorrente.impostaPartita();
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
		if (this.partitaCorrente == null)
			return;
			
	    for (Giocatore g : partitaCorrente.getAllGiocatori().values()) {
	        if (g.getPedina() != null) {
	            g.getPedina().setPosizione(1);
	            g.getPedina().setStato(1);
	        }
	    }
		this.partitaCorrente = null;
	}


	public Map<Integer, String> getNomiGiocatoriDefault() {
		return nomiGiocatoriDefault;
	}

	public String getCodiceScenarioDefault() {
		return codiceScenarioDefault;
	}

	public String getCodiceRegoleSetDefault() {
		return codiceRegoleSetDefault;
	}

	public Map<Integer, String> getCodiciDadiGiocatoriDefault() {
		return codiciDadiGiocatoriDefault;
	}

	public Map<Integer, String> getCodiciPedineGiocatoriDefault() {
		return codiciPedineGiocatoriDefault;
	}
	
	public void salvaImpostazioni(String nomeDefaultG1, String nomeDefaultG2, String nomeDefaultG3, String nomeDefaultG4,
								  String codiceRegoleSetDefault, String codiceScenarioDefault,
								  String codiceDadoDefaultG1, String codiceDadoDefaultG2, String codiceDadoDefaultG3, String codiceDadoDefaultG4,
								  String codicePedinaDefaultG1, String codicePedinaDefaultG2, String codicePedinaDefaultG3, String codicePedinaDefaultG4) 
	{
		this.nomiGiocatoriDefault.clear();		
		this.nomiGiocatoriDefault.put(1, nomeDefaultG1);
		this.nomiGiocatoriDefault.put(2, nomeDefaultG2);
		this.nomiGiocatoriDefault.put(3, nomeDefaultG3);
		this.nomiGiocatoriDefault.put(4, nomeDefaultG4);
		
		this.codiceRegoleSetDefault = codiceRegoleSetDefault;
		
		this.codiceScenarioDefault = codiceScenarioDefault;
		
		this.codiciDadiGiocatoriDefault.clear();
		this.codiciDadiGiocatoriDefault.clear();		
		this.codiciDadiGiocatoriDefault.put(1, codiceDadoDefaultG1);
		this.codiciDadiGiocatoriDefault.put(2, codiceDadoDefaultG2);
		this.codiciDadiGiocatoriDefault.put(3, codiceDadoDefaultG3);
		this.codiciDadiGiocatoriDefault.put(4, codiceDadoDefaultG4);

		this.codiciPedineGiocatoriDefault.clear();
		this.codiciPedineGiocatoriDefault.clear();		
		this.codiciPedineGiocatoriDefault.put(1, codicePedinaDefaultG1);
		this.codiciPedineGiocatoriDefault.put(2, codicePedinaDefaultG2);
		this.codiciPedineGiocatoriDefault.put(3, codicePedinaDefaultG3);
		this.codiciPedineGiocatoriDefault.put(4, codicePedinaDefaultG4);
	}
	
	public void impostaRegoleSingole(javax.swing.table.TableModel tableModel) {
	    partitaCorrente.impostaRegoleSingole(this.listaRegoleSingole, tableModel);
	}

	public void impostaRegoleDaSet(javax.swing.table.TableModel tableModel) {
		partitaCorrente.impostaRegoleDaSet(this.mapRegoleSet, tableModel);
	}
	
	public void impostaScenario(String codiceScenario) {
        this.partitaCorrente.impostaScenario(codiceScenario, this.listaScenari);
	}
	
	public void impostaPedinaGiocatore(String codicePedina, int numeroGiocatore, boolean editGiocatore) {
	        partitaCorrente.impostaPedinaGiocatore(codicePedina, numeroGiocatore, listaPersonalizzazioni, editGiocatore);	    
	}

	public void impostaDadoGiocatore(String codiceDado, int numeroGiocatore, boolean editGiocatore) {
	        partitaCorrente.impostaDadoGiocatore(codiceDado, numeroGiocatore, listaPersonalizzazioni, editGiocatore);	    
	}
	
	public MossaResult eseguiTurnoGiocatore(Pedina pedinaCorrente, int risultatoDado) {
	    return partitaCorrente.applicaMossaConMessaggi(pedinaCorrente, risultatoDado);
	}
	
	public void aggiungiGiocatoriOspiti(int numeroGiocatori) {
	    partitaCorrente.aggiungiGiocatoriOspiti(numeroGiocatori, this.nomiGiocatoriDefault);
	}
	
	public void configuraGiocatoreCorrente(
	        int numeroGiocatore,
	        javax.swing.table.TableModel tablePedinaSelezionataModel,
	        javax.swing.table.TableModel tableDadoSelezionatoModel,
	        List<Personalizzazione> personalizzazioni) {
	        partitaCorrente.configuraGiocatore(
	            numeroGiocatore,
	            tablePedinaSelezionataModel,
	            tableDadoSelezionatoModel,
	            personalizzazioni
	        );    
	}


	public void replaceAllScenari(List<Scenario> nuovi) {
	    this.listaScenari.clear();
	    this.listaScenari.addAll(nuovi);
	}
	
	public void replaceAllSetRegole(Map<String, Set<Regola>> nuoviSet) {
	    this.mapRegoleSet.clear();
	    this.mapRegoleSet.putAll(nuoviSet);
	}



}
