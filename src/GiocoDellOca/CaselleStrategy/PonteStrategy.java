package GiocoDellOca.CaselleStrategy;

import GiocoDellOca.*;
import java.util.List;
import java.util.Map;

public class PonteStrategy implements CasellaStrategyInterface {
    @Override
    public void apply(Pedina pedina, int risultatoDado, int pos,
                      Map<Integer, Casella> caselleMap,
                      Map<Integer, Giocatore> giocatori,
                      Scenario scenario, List<String> msg) {

        String descr = (scenario != null && scenario.getDescrizioneCasellaPonte() != null)
                ? scenario.getDescrizioneCasellaPonte() + " -> " + pos
                : "Ponte! Avanzi di " + pos + " caselle.";
        msg.add(descr);

        int rimbalzo = pedina.Muovi(pos, caselleMap.size());
        if (rimbalzo != 0)
            msg.add("Hai superato la casella finale! Torni indietro di " + rimbalzo + " caselle.");
    }
}
