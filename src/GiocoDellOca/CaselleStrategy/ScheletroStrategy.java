package GiocoDellOca.CaselleStrategy;

import GiocoDellOca.*;
import java.util.List;
import java.util.Map;

public class ScheletroStrategy implements CasellaStrategyInterface {
    @Override
    public void apply(Pedina pedina, int risultatoDado, int pos,
                      Map<Integer, Casella> caselleMap,
                      Map<Integer, Giocatore> giocatori,
                      Scenario scenario, List<String> msg) {

        String descr = (scenario != null && scenario.getDescrizioneCasellaScheletro() != null)
                ? scenario.getDescrizioneCasellaScheletro()
                : "Scheletro! Torni alla casella iniziale.";
        msg.add(descr);
        pedina.setPosizione(1);
    }
}

