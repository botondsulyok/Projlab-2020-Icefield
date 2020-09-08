package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.command.CommandUtils;
import hu.minesweepers.modell.control.Commandable;
import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Building;
import hu.minesweepers.modell.tile.building.Igloo;
import hu.minesweepers.modell.tile.building.Plains;
import hu.minesweepers.modell.tile.building.Tent;


/**
 * A statikus osztálynak a felelőssége kezelni a "building" parancsot, illetve tartalmazza az épülettípusokat.
 */
public class BuildingCommand {

    /**
     *Az épülettípusok felsorolása.
     */
    public enum BuildingType {

        /**
         * Az épülettípus sík
         */
        PLAINS("plains") {
            @Override
            Building createObject(IceUnit iu, String unique) {
                return new Plains(iu, unique);
            }
        },

        /**
         * Az épülettípus sátor
         */
        TENT("tent"){
            @Override
            Building createObject(IceUnit iu, String unique) {
                return new Tent(iu, unique);
            }
        },

        /**
         * Az épülettípus iglu
         */
        IGLOO("igloo"){
            @Override
            Building createObject(IceUnit iu, String unique) {
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
        abstract Building createObject(IceUnit iu, String unique);
    }

    /**
     * Ez a függvény oldja meg a "building" parancsot. A paranccsal egy kiválasztott karakterrel építhetünk egy épületet a kiválasztott jégtáblára.
     * @param buildingID Az új épület azonosítója.
     * @param type Az épület típusa, amit építeni akarunk a karakterrel.
     * @param fieldID A jégtábla azonosítója, ahova az épületet szeretnénk építeni.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítókat vagy épülettípust adtunk meg a parancs argumentumokban.
     */
    public static boolean building(String buildingID, BuildingType type, String fieldID) {
        if (type == null) {
            return false;
        }
        IceUnit iu = CommandUtils.getIceUnit(fieldID);
        if (iu == null) {
            return false;
        }
        Building b = type.createObject(iu, buildingID);

        if(type.type.equals("tent"))
            GameController.getInstance().addCommandable((Commandable) b);
        iu.build(b);
        return true;
    }
}
