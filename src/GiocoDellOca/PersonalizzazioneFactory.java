package GiocoDellOca;

import java.util.List;

public class PersonalizzazioneFactory {

    public static Dado creaDadoDaCodice(String codice, List<Personalizzazione> lista) {
        for (var p : lista) {
            if (p instanceof Dado && p.getCodicePersonalizzazione().equals(codice)) {
                return (Dado) p;
            }
        }
        // Dado di default
        return new Dado("Dado_Classico", "Il dado del giocatore è il dado classico", "./src/images/dadoclassico_1.png");
    }

    public static Pedina creaPedinaDaCodice(String codice, List<Personalizzazione> lista) {
        for (var p : lista) {
            if (p instanceof Pedina && p.getCodicePersonalizzazione().equals(codice)) {
                return (Pedina) p;
            }
        }
        // Pedina di default
        return new Pedina("Pedina_Oca", "La pedina del giocatore è un'oca", "./src/images/ScarfGoose.png");
    }
}

