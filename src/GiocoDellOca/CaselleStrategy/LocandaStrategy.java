package GiocoDellOca.CaselleStrategy;

import GiocoDellOca.*;
import java.util.List;
import java.util.Map;

public class LocandaStrategy implements CasellaStrategyInterface {
    @Override
    public void apply(Pedina pedina, int risultatoDado, int pos,
                      Map<Integer, Casella> caselleMap,
                      Map<Integer, Giocatore> giocatori,
                      Scenario scenario, List<String> msg) {

        String descr = (scenario != null && scenario.getDescrizioneCasellaLocanda() != null)
                ? scenario.getDescrizioneCasellaLocanda()
                : "Locanda! La tua pedina è ferma per un turno.";
        msg.add(descr);
        pedina.setStato(0);
    }
}

