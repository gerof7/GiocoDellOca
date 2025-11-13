package GiocoDellOca.CaselleStrategy;

import GiocoDellOca.*;
import java.util.List;
import java.util.Map;

public class LabirintoStrategy implements CasellaStrategyInterface {
    @Override
    public void apply(Pedina pedina, int risultatoDado, int pos,
                      Map<Integer, Casella> caselleMap,
                      Map<Integer, Giocatore> giocatori,
                      Scenario scenario, List<String> msg) {

        String descr = (scenario != null && scenario.getDescrizioneCasellaLabirinto() != null)
                ? scenario.getDescrizioneCasellaLabirinto()
                : "Labirinto! Torni indietro di 3 caselle.";
        msg.add(descr);
        pedina.Muovi(-3, caselleMap.size());
    }
}

