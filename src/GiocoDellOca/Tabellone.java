package GiocoDellOca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tabellone {
	
	private Map<Integer, Casella> caselleMap;
	private int numeroDadi;

	public Tabellone(List<Regola> listaRegole, Scenario scenario) {
		
		caselleMap = new HashMap<>();
		
		var numeroCasellaFine = 63;
		this.numeroDadi = 2;
		
		for (var regola : listaRegole) {
			if (regola.getTipologiaRegola() == TipologiaRegolaEnum.NumeroCaselle) {
				numeroCasellaFine = Integer.parseInt(regola.getProprietaRegola());
			}
			else if (regola.getTipologiaRegola() == TipologiaRegolaEnum.NumeroDadi) {
				this.numeroDadi = Integer.parseInt(regola.getProprietaRegola());
			}
			else
				continue;
		}
		
		var casellaInizio = new CasellaNormale(1, "Casella Iniziale");
		caselleMap.put(1, casellaInizio);
		
		var casellaFine = new CasellaFine(numeroCasellaFine);
		caselleMap.put(numeroCasellaFine, casellaFine);
			
		List<Integer> posizioniOcaOriginali = List.of(5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59);
		List<Integer> nuovePosizioniOca = calcolaPosizioniCaselleSpeciali(numeroCasellaFine, posizioniOcaOriginali);
		
		for (var posizioneOca : nuovePosizioniOca) {
			var casellaOca = new CasellaSpeciale(posizioneOca, scenario.getDescrizioneCasellaOca(), TipologiaCasellaSpecialeEnum.Oca);
			caselleMap.putIfAbsent(posizioneOca, casellaOca);
		}
		
		List<Integer> posizioniPrigioneOriginali = List.of(31, 52);
		List<Integer> nuovePosizioniPrigione = calcolaPosizioniCaselleSpeciali(numeroCasellaFine, posizioniPrigioneOriginali);
		
		for (var posizionePrigione : nuovePosizioniPrigione) {
			var casellaPrigione = new CasellaSpeciale(posizionePrigione, scenario.getDescrizioneCasellaPrigione(), TipologiaCasellaSpecialeEnum.Prigione);
			caselleMap.putIfAbsent(posizionePrigione, casellaPrigione);
		}
		
		int posizionePonteOriginale = 6;
		int nuovaPosizionePonte = calcolaPosizioneCasellaSpeciale(numeroCasellaFine, posizionePonteOriginale);
		
		var casellaPonte = new CasellaSpeciale(nuovaPosizionePonte, scenario.getDescrizioneCasellaPonte(), TipologiaCasellaSpecialeEnum.Ponte);
		caselleMap.putIfAbsent(nuovaPosizionePonte, casellaPonte);
		
		int posizioneLocandaOriginale = 19;
		int nuovaPosizioneLocanda = calcolaPosizioneCasellaSpeciale(numeroCasellaFine, posizioneLocandaOriginale);
		
		var casellaLocanda = new CasellaSpeciale(nuovaPosizioneLocanda, scenario.getDescrizioneCasellaLocanda(), TipologiaCasellaSpecialeEnum.Locanda);
		caselleMap.putIfAbsent(nuovaPosizioneLocanda, casellaLocanda);
			
		int posizioneLabirintoOriginale = 42;
		int nuovaPosizioneLabirinto = calcolaPosizioneCasellaSpeciale(numeroCasellaFine, posizioneLabirintoOriginale);
		
		var casellaLabirinto= new CasellaSpeciale(nuovaPosizioneLabirinto, scenario.getDescrizioneCasellaLabirinto(), TipologiaCasellaSpecialeEnum.Labirinto);
		caselleMap.putIfAbsent(nuovaPosizioneLabirinto, casellaLabirinto);
		
		int posizioneScheletroOriginale = 58;
		int nuovaPosizioneScheletro = calcolaPosizioneCasellaSpeciale(numeroCasellaFine, posizioneScheletroOriginale);	
		
		var casellaScheletro= new CasellaSpeciale(nuovaPosizioneScheletro, scenario.getDescrizioneCasellaScheletro(), TipologiaCasellaSpecialeEnum.Scheletro);
		caselleMap.putIfAbsent(nuovaPosizioneScheletro, casellaScheletro);
		
		var keySet = caselleMap.keySet();
		
		for (int i = 2; i < numeroCasellaFine ; i++) {
			if(!keySet.contains(i)) {
				var casellaNormale = new CasellaNormale(i, "");
				caselleMap.putIfAbsent(i, casellaNormale);
			}
			else
				continue;
		}
				
	}

	public Map<Integer, Casella> getCaselleMap() {
		return caselleMap;
	}
	
	private int calcolaPosizioneCasellaSpeciale(int numeroCasellaFine, int posizioneOriginale) {
		double nuovaPosizione = ((double)posizioneOriginale/63) * numeroCasellaFine;
		return (int) Math.round(nuovaPosizione);
	}
	
	private List<Integer> calcolaPosizioniCaselleSpeciali(int numeroCasellaFine, List<Integer> posizioniOriginali) {
		List<Integer> nuovePosizioni = new ArrayList<>();
        double fattoreProporzione = (double) numeroCasellaFine / 63;
        
        for (int posizioneOriginale : posizioniOriginali) {
            int nuovaPosizione = (int) Math.round(posizioneOriginale * fattoreProporzione);
            nuovePosizioni.add(nuovaPosizione);
        }
        
        return nuovePosizioni;
    }

	public int getNumeroDadi() {
		return numeroDadi;
	}
	
}
