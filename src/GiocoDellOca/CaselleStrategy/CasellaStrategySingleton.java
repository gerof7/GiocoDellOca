package GiocoDellOca.CaselleStrategy;

import GiocoDellOca.TipologiaCasellaSpecialeEnum;
import java.util.EnumMap;
import java.util.Map;

public class CasellaStrategySingleton {

    private static CasellaStrategySingleton instance;
    private final Map<TipologiaCasellaSpecialeEnum, CasellaStrategyInterface> strategie = new EnumMap<>(TipologiaCasellaSpecialeEnum.class);

    private CasellaStrategySingleton() {
        strategie.put(TipologiaCasellaSpecialeEnum.Oca,       new OcaStrategy());
        strategie.put(TipologiaCasellaSpecialeEnum.Ponte,     new PonteStrategy());
        strategie.put(TipologiaCasellaSpecialeEnum.Locanda,   new LocandaStrategy());
        strategie.put(TipologiaCasellaSpecialeEnum.Prigione,  new PrigioneStrategy());
        strategie.put(TipologiaCasellaSpecialeEnum.Labirinto, new LabirintoStrategy());
        strategie.put(TipologiaCasellaSpecialeEnum.Scheletro, new ScheletroStrategy());
    }

    public static CasellaStrategySingleton getInstance() {
        if (instance == null) {
            instance = new CasellaStrategySingleton();
        }
        return instance;
    }

    public CasellaStrategyInterface getStrategy(TipologiaCasellaSpecialeEnum tipo) {
        return strategie.get(tipo);
    }
}

