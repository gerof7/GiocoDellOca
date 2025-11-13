package GiocoDellOca.CaselleStrategy;

import GiocoDellOca.*;
import java.util.List;
import java.util.Map;

public class PrigioneStrategy implements CasellaStrategyInterface {
    @Override
    public void apply(Pedina pedina, int risultatoDado, int pos,
                      Map<Integer, Casella> caselleMap,
                      Map<Integer, Giocatore> giocatori,
                      Scenario scenario, List<String> msg) {

        String descr = (scenario != null && scenario.getDescrizioneCasellaPrigione() != null)
                ? scenario.getDescrizioneCasellaPrigione()
                : "Prigione! Resti imprigionato finchè un altro giocatore non arriva su questa casella.";
        msg.add(descr);
        pedina.setStato(0);

        for (Giocatore g : giocatori.values()) {
            Pedina px = g.getPedina();
            if (px != null && px != pedina && px.getPosizione() == pedina.getPosizione()) {
                px.setStato(1);
                msg.add("Un'altra pedina è stata rilasciata dalla prigione!");
            }
        }
    }
}
