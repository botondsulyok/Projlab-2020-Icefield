package hu.minesweepers.controller.parser;

import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Building;
import hu.minesweepers.modell.tile.building.Igloo;
import hu.minesweepers.modell.tile.building.Plains;
import hu.minesweepers.modell.tile.building.Tent;

/**
 *Az épülettípusok felsorolása.
 */
public enum BuildingType {

    /**
     * Az épülettípus sík
     */
    PLAINS("plains") {
        @Override
        public Building createObject(IceUnit iu, String unique) {
            return new Plains(iu, unique);
        }
    },

    /**
     * Az épülettípus sátor
     */
    TENT("tent"){
        @Override
        public Building createObject(IceUnit iu, String unique) {
            return new Tent(iu, unique);
        }
    },

    /**
     * Az épülettípus iglu
     */
    IGLOO("igloo"){
        @Override
        public Building createObject(IceUnit iu, String unique) {
            return new Igloo(iu, unique);
        }
    };

    /**
     * Eltárolja az épülettípusát stringben.
     */
    private String type;

    /**
     * Az épülettípus konstruktora.
     * @param type A stringben kapott épülettípus.
     */
    BuildingType(String type) {
        this.type = type;
    }

    /**
     * Átalakítja a stringben kapott épülettípust enum épülettípussá és azt adja vissza.
     * @param type A stringben kapott épülettípust.
     * @return Visszaadja az enum épülettípust. Ha nincs a paraméterrel megegyező épület, akkor null-t ad vissza.
     */
    public static BuildingType fromString(String type) {
        for (BuildingType b : BuildingType.values()) {
            if (b.type.equalsIgnoreCase(type)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Absztrakt függvény, az épülettípusok maguk írják felül a függvényt és hozzák létre a megfelelő épület objektumot, majd ezt visszaadják.
     * @param iu A jégtábla, amin az épületet elhelyezzük.
     * @param unique Az új épület azonosítója.
     * @return Visszaadja a létrehozott épületet.
     */
    public abstract Building createObject(IceUnit iu, String unique);
}
