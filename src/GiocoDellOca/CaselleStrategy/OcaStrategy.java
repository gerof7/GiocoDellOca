package GiocoDellOca.CaselleStrategy;

import GiocoDellOca.*;
import java.util.List;
import java.util.Map;

public class OcaStrategy implements CasellaStrategyInterface {
    @Override
    public void apply(Pedina pedina, int risultatoDado, int pos,
                      Map<Integer, Casella> caselleMap,
                      Map<Integer, Giocatore> giocatori,
                      Scenario scenario, List<String> msg) {

        String descr = (scenario != null && scenario.getDescrizioneCasellaOca() != null)
                ? scenario.getDescrizioneCasellaOca() + " -> " + risultatoDado
                : "Oca! Avanzi di " + risultatoDado + " caselle.";
        msg.add(descr);

        int rimbalzo = pedina.Muovi(risultatoDado, caselleMap.size());
        if (rimbalzo != 0)
            msg.add("Hai superato la casella finale! Torni indietro di " + rimbalzo + " caselle.");
    }
}

