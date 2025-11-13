package GiocoDellOca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import GiocoDellOca.CaselleStrategy.CasellaStrategyInterface;
import GiocoDellOca.CaselleStrategy.CasellaStrategySingleton;
import Utilities.MossaResult;

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

	public void impostaPedinaGiocatore(String codicePedina, int numeroGiocatore, List<Personalizzazione> personalizzazioni, boolean editGiocatore) {
	    if (editGiocatore) {
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

	public void impostaDadoGiocatore(String codiceDado, int numeroGiocatore, List<Personalizzazione> personalizzazioni, boolean editGiocatore) {
	    if (editGiocatore) {
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

//	public MossaResult applicaMossaConMessaggi(Pedina pedinaCorrente, int risultatoDado) {
//	    List<String> messaggi = new ArrayList<>();
//	    Map<Integer, Casella> caselleMap = tabellone.getCaselleMap();
//	
//	    Scenario scenarioCorrente = impostazioni != null ? impostazioni.getScenario() : null;
//	
//	    if (pedinaCorrente.getStato() == 0) {
//	        Casella casellaAttuale = caselleMap.get(pedinaCorrente.getPosizione());
//	
//	        if (casellaAttuale instanceof CasellaSpeciale casellaSpeciale &&
//	            casellaSpeciale.getTipologiaCasellaSpeciale() == TipologiaCasellaSpecialeEnum.Prigione) {
//	
//	            String descr = (scenarioCorrente != null && scenarioCorrente.getDescrizioneCasellaPrigione() != null)
//	                    ? scenarioCorrente.getDescrizioneCasellaPrigione()
//	                    : "La tua pedina è ferma perché sei in prigione. Salti il turno.";
//	            messaggi.add(descr);
//	            return MossaResult.ongoing(messaggi);
//	        }
//	
//	        messaggi.add("La tua pedina è ferma per questo turno. Salti il turno.");
//	        pedinaCorrente.setStato(1);
//	        return MossaResult.ongoing(messaggi);
//	    }
//	
//	    int posizioneIniziale = pedinaCorrente.getPosizione();
//	    int posizioneFinale = caselleMap.size();
//	    int nuovaPosizione = posizioneIniziale + risultatoDado;
//	    boolean superataCasellaFinale = false;
//	
//	    if (nuovaPosizione > posizioneFinale) {
//	        int differenza = nuovaPosizione - posizioneFinale;
//	        nuovaPosizione = posizioneFinale - differenza;
//	        messaggi.add("Hai superato la casella finale! Torni indietro di " + differenza + " caselle.");
//	        pedinaCorrente.setPosizione(nuovaPosizione);
//	        superataCasellaFinale = true;
//	    } else {
//	        int rimbalzo = pedinaCorrente.Muovi(risultatoDado, caselleMap.size());
//	        if (rimbalzo != 0)
//	            messaggi.add("Hai superato la casella finale! Torni indietro di " + rimbalzo + " caselle.");
//	    }
//	
//	    int posizioneCorrente = pedinaCorrente.getPosizione();
//	    Casella casellaAttuale = caselleMap.get(posizioneCorrente);
//	
//	    if (!superataCasellaFinale) {
//	        if (casellaAttuale instanceof CasellaFine) {
//	            messaggi.add("Complimenti! Hai raggiunto la fine e vinto il gioco!");
//	            return MossaResult.finished(messaggi, "Complimenti! Hai raggiunto la fine e vinto il gioco!");
//	        } 
//	        else if (casellaAttuale instanceof CasellaSpeciale casellaSpeciale) {
//	            TipologiaCasellaSpecialeEnum tipo = casellaSpeciale.getTipologiaCasellaSpeciale();
//	
//	            switch (tipo) {
//	                case Oca -> {
//	                    String descr = (scenarioCorrente != null && scenarioCorrente.getDescrizioneCasellaOca() != null)
//	                            ? scenarioCorrente.getDescrizioneCasellaOca() + " -> " + risultatoDado
//	                            : "Oca! Avanzi di " + risultatoDado + " caselle.";
//	                    messaggi.add(descr);
//	
//	                    int rimbalzo = pedinaCorrente.Muovi(risultatoDado, caselleMap.size());
//	                    if (rimbalzo != 0)
//	                        messaggi.add("Hai superato la casella finale! Torni indietro di " + rimbalzo + " caselle.");
//	                }
//	                case Ponte -> {
//	                    String descr = (scenarioCorrente != null && scenarioCorrente.getDescrizioneCasellaPonte() != null)
//	                            ? scenarioCorrente.getDescrizioneCasellaPonte() + " -> " + posizioneCorrente
//	                            : "Ponte! Avanzi di " + posizioneCorrente + " caselle.";
//	                    messaggi.add(descr);
//	
//	                    int rimbalzo = pedinaCorrente.Muovi(posizioneCorrente, caselleMap.size());
//	                    if (rimbalzo != 0)
//	                        messaggi.add("Hai superato la casella finale! Torni indietro di " + rimbalzo + " caselle.");
//	                }
//	                case Locanda -> {
//	                    String descr = (scenarioCorrente != null && scenarioCorrente.getDescrizioneCasellaLocanda() != null)
//	                            ? scenarioCorrente.getDescrizioneCasellaLocanda()
//	                            : "Locanda! La tua pedina è ferma per un turno.";
//	                    messaggi.add(descr);
//	                    pedinaCorrente.setStato(0);
//	                }
//	                case Prigione -> {
//	                    String descr = (scenarioCorrente != null && scenarioCorrente.getDescrizioneCasellaPrigione() != null)
//	                            ? scenarioCorrente.getDescrizioneCasellaPrigione()
//	                            : "Prigione! Resti imprigionato finchè un altro giocatore non arriva su questa casella.";
//	                    messaggi.add(descr);
//	                    pedinaCorrente.setStato(0);
//	
//	                    for (Giocatore altra : giocatori.values()) {
//	                        Pedina altraPedina = altra.getPedina();
//	                        if (altraPedina != pedinaCorrente &&
//	                            altraPedina.getPosizione() == pedinaCorrente.getPosizione()) {
//	                            altraPedina.setStato(1);
//	                            messaggi.add("Un'altra pedina è stata rilasciata dalla prigione!");
//	                        }
//	                    }
//	                }
//	                case Labirinto -> {
//	                    String descr = (scenarioCorrente != null && scenarioCorrente.getDescrizioneCasellaLabirinto() != null)
//	                            ? scenarioCorrente.getDescrizioneCasellaLabirinto()
//	                            : "Labirinto! Torni indietro di 3 caselle.";
//	                    messaggi.add(descr);
//	                    pedinaCorrente.Muovi(-3, caselleMap.size());
//	                }
//	                case Scheletro -> {
//	                    String descr = (scenarioCorrente != null && scenarioCorrente.getDescrizioneCasellaScheletro() != null)
//	                            ? scenarioCorrente.getDescrizioneCasellaScheletro()
//	                            : "Scheletro! Torni alla casella iniziale.";
//	                    messaggi.add(descr);
//	                    pedinaCorrente.setPosizione(1);
//	                }
//	                default -> messaggi.add("Errore: tipo di casella sconosciuto.");
//	            }
//	        }
//	    }
//	
//	    return MossaResult.ongoing(messaggi);
//	}
//	
	
	public MossaResult applicaMossaConMessaggi(Pedina pedinaCorrente, int risultatoDado) {
        List<String> messaggi = new ArrayList<>();
        Map<Integer, Casella> caselleMap = tabellone.getCaselleMap();
        Scenario scenarioCorrente = (impostazioni != null) ? impostazioni.getScenario() : null;

        if (pedinaCorrente.getStato() == 0) {
            Casella casellaAttuale0 = caselleMap.get(pedinaCorrente.getPosizione());

            if (casellaAttuale0 instanceof CasellaSpeciale cs
                    && cs.getTipologiaCasellaSpeciale() == TipologiaCasellaSpecialeEnum.Prigione) {

                String descr = (scenarioCorrente != null && scenarioCorrente.getDescrizioneCasellaPrigione() != null)
                        ? scenarioCorrente.getDescrizioneCasellaPrigione()
                        : "La tua pedina è ferma perché sei in prigione. Salti il turno.";
                messaggi.add(descr);
                return MossaResult.ongoing(messaggi);
            }

            messaggi.add("La tua pedina è ferma per questo turno. Salti il turno.");
            pedinaCorrente.setStato(1);
            return MossaResult.ongoing(messaggi);
        }

        int posizioneIniziale = pedinaCorrente.getPosizione();
        int posizioneFinale = caselleMap.size();
        int nuovaPosizione = posizioneIniziale + risultatoDado;
        boolean superataCasellaFinale = false;

        if (nuovaPosizione > posizioneFinale) {
            int differenza = nuovaPosizione - posizioneFinale;
            nuovaPosizione = posizioneFinale - differenza;
            messaggi.add("Hai superato la casella finale! Torni indietro di " + differenza + " caselle.");
            pedinaCorrente.setPosizione(nuovaPosizione);
            superataCasellaFinale = true;
        } else {
            int rimbalzo = pedinaCorrente.Muovi(risultatoDado, caselleMap.size());
            if (rimbalzo != 0)
                messaggi.add("Hai superato la casella finale! Torni indietro di " + rimbalzo + " caselle.");
        }

        int posizioneCorrente = pedinaCorrente.getPosizione();
        Casella casellaAttuale = caselleMap.get(posizioneCorrente);

        if (!superataCasellaFinale) {
        	if (casellaAttuale instanceof CasellaFine) {

        	    String nomeVincitore = "Bot";
        	    for (Giocatore g : giocatori.values()) {
        	        if (g.getPedina() == pedinaCorrente) {
        	            nomeVincitore = g.getNome();
        	            break;
        	        }
        	    }

        	    String msgVittoria = nomeVincitore + " ha raggiunto la fine e vinto il gioco!";

        	    messaggi.add(msgVittoria);

        	    return MossaResult.finished(messaggi, msgVittoria);
        	}


            if (casellaAttuale instanceof CasellaSpeciale cs) {
                TipologiaCasellaSpecialeEnum tipo = cs.getTipologiaCasellaSpeciale();

                CasellaStrategyInterface strategy =
                        CasellaStrategySingleton.getInstance().getStrategy(tipo);

                if (strategy != null) {
                    strategy.apply(
                        pedinaCorrente,
                        risultatoDado,
                        posizioneCorrente,
                        caselleMap,
                        giocatori,
                        scenarioCorrente,
                        messaggi
                    );
                } else {
                    messaggi.add("Errore: tipo di casella sconosciuto.");
                }
            }
        }

        return MossaResult.ongoing(messaggi);
    }
	
	public void configuraGiocatore(
	        int numeroGiocatore,
	        javax.swing.table.TableModel tablePedinaSelezionataModel,
	        javax.swing.table.TableModel tableDadoSelezionatoModel,
	        List<Personalizzazione> personalizzazioni
	) {
	    Giocatore giocatore = getGiocatore(numeroGiocatore);
	    if (giocatore == null) return;

	    Pedina pedina;
	    if (tablePedinaSelezionataModel.getRowCount() > 0) {
	        String codicePedina = (String) tablePedinaSelezionataModel.getValueAt(0, 0);
	        String descrizionePedina = (String) tablePedinaSelezionataModel.getValueAt(0, 1);

	        String pathPedina = personalizzazioni.stream()
	                .filter(p -> p instanceof Pedina && p.getCodicePersonalizzazione().equals(codicePedina))
	                .map(Personalizzazione::getPath)
	                .findFirst()
	                .orElse("./src/images/ScarfGoose.png");

	        pedina = new Pedina(codicePedina, descrizionePedina, pathPedina);
	    } else {
	        pedina = new Pedina("Pedina_Oca", "La pedina del giocatore è un'oca", "./src/images/ScarfGoose.png");
	    }
	    giocatore.setPedina(pedina);

	    Dado dado;
	    if (tableDadoSelezionatoModel.getRowCount() > 0) {
	        String codiceDado = (String) tableDadoSelezionatoModel.getValueAt(0, 0);
	        String descrizioneDado = (String) tableDadoSelezionatoModel.getValueAt(0, 1);

	        String pathDado = personalizzazioni.stream()
	                .filter(p -> p instanceof Dado && p.getCodicePersonalizzazione().equals(codiceDado))
	                .map(Personalizzazione::getPath)
	                .findFirst()
	                .orElse("./src/images/dadoclassico_1.png");

	        dado = new Dado(codiceDado, descrizioneDado, pathDado);
	    } else {
	        dado = new Dado("Dado_Classico", "Il dado del giocatore è il dado classico", "./src/images/dadoclassico_1.png");
	    }
	    giocatore.setDado(dado);
	}



	
}
