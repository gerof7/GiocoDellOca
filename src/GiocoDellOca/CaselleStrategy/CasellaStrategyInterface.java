package GiocoDellOca.CaselleStrategy;

import GiocoDellOca.*;
import java.util.List;
import java.util.Map;

public interface CasellaStrategyInterface {
    void apply(
        Pedina pedinaCorrente,
        int risultatoDado,
        int posizioneCorrente,
        Map<Integer, Casella> caselleMap,
        Map<Integer, Giocatore> giocatori,
        Scenario scenarioCorrente,
        List<String> messaggi
    );
}

