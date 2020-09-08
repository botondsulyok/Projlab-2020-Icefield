package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.command.CommandUtils;
import hu.minesweepers.modell.items.Pickable;
import hu.minesweepers.modell.items.components.Cartridge;
import hu.minesweepers.modell.items.components.Light;
import hu.minesweepers.modell.items.components.Pistol;
import hu.minesweepers.modell.items.consumable.Food;
import hu.minesweepers.modell.items.digger.BreakableShovel;
import hu.minesweepers.modell.items.digger.Shovel;
import hu.minesweepers.modell.items.protector.DivingSuit;
import hu.minesweepers.modell.items.puller.Rope;
import hu.minesweepers.modell.tile.IceUnit;
import hu.minesweepers.modell.tile.building.Tent;


/**
 * A statikus osztálynak a felelőssége kezelni az "item" parancsot, illetve tartalmazza a tárgytípusokat.
 */
public class ItemCommand {

    /**
     * A tárgytípusok felsorolása.
     */
    public enum ItemType {

        /**
         * A tárgytípus pisztoly
         */
        PISTOL("pistol") {
            @Override
            Pickable createObject(String unique, int d) {
                return new Pistol(unique);
            }
        },

        /**
         * A tárgytípus töltény
         */
        CARTRIDGE("cartridge") {
            @Override
            Pickable createObject(String unique, int d) {
                return new Cartridge(unique);
            }
        },

        /**
         * A tárgytípus fény
         */
        LIGHT("light") {
            @Override
            Pickable createObject(String unique, int d) {
                return new Light(unique);
            }
        },

        /**
         * A tárgytípus búvárruha
         */
        SUIT("suit") {
            @Override
            Pickable createObject(String unique, int d) {
                return new DivingSuit(unique);
            }
        },

        /**
         * A tárgytípus kötél
         */
        ROPE("rope") {
            @Override
            Pickable createObject(String unique, int d) {
                return new Rope(unique);
            }
        },

        /**
         * A tárgytípus ásó
         */
        SHOVEL("shovel") {
            @Override
            Pickable createObject(String unique, int d) {
                return new Shovel(2, unique);
            }
        },

        /**
         * A tárgytípus eltörhető ásó
         */
        BREAKABLESHOVEL("breakable") {
            @Override
            Pickable createObject(String unique, int d) {
                int durability = d == -1 ? 3 : d;
                return new BreakableShovel(2, durability, unique);
            }
        },

        /**
         * A tárgytípus étel
         */
        FOOD("food") {
            @Override
            Pickable createObject(String unique, int d) {
                return new Food(1, unique);
            }
        },

        /**
         * A tárgytípus sátor
         */
        TENT("tent") {
            @Override
            Pickable createObject(String unique, int d) {
                return new Tent(null, unique);
            }
        };

        /**
         * Eltárolja a tárgytípusát stringben.
         */
        private String type;

        /**
         * A tárgytípus konstruktora.
         * @param type A stringben kapott tárgytípus.
         */
        ItemType(String type) {
            this.type = type;
        }

        /**
         * Átalakítja a stringben kapott tárgytípust enum tárgytípussá és azt adja vissza.
         * @param type A stringben kapott tárgytípus.
         * @return Visszaadja az enum tárgytípust. Ha nincs a paraméterrel megegyező tárgy, akkor null-t ad vissza.
         */
        public static ItemType fromString(String type) {
            for (ItemType i : ItemType.values()) {
                if (i.type.equalsIgnoreCase(type)) {
                    return i;
                }
            }
            return null;
        }

        /**
         * Absztrakt függvény, a tárgytípusok maguk írják felül a függvényt és hozzák létre a megfelelő tárgy objektumot, majd ezt visszaadják.
         * @param unique Az új tárgy azonosítója.
         * @param d Ha a tárgynak van maximum használati száma, akkor a paraméter ezt tartalmazza.
         * @return Visszaadja a létrehozott tárgyat.
         */
        abstract Pickable createObject(String unique, int d);
    }

    /**
     * Ez a függvény oldja meg az "item" parancsot. A paranccsal létrehozhatunk egy tárgyat, amit hozzáadhatunk egy karakterhez vagy egy jégtáblához.
     * @param itemID Az új tárgy azonosítója.
     * @param type A tárgynak a típusa.
     * @param ownerID A karakter vagy jégtábla azonosítója, ahova szeretnénk adni a tárgyat.
     * @param durability A tárgy tartóssága.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó tárgytípust vagy nem jó azonosítót adtunk meg a parancs argumentumában.
     */
    public static boolean item(String itemID, ItemType type, String ownerID, int durability) {
        if (type == null) {
            return false;
        }
        Character c = CommandUtils.getCharacter(ownerID);
        IceUnit iu = CommandUtils.getIceUnit(ownerID);

        Pickable p = type.createObject(itemID, durability);
        if (c != null) {
            p.pickedBy(c, new IceUnit(0, null));
        }
        else if (iu != null) {
            iu.setPickable(p);
        }
        else {
            return false;
        }
        return true;
    }
}
