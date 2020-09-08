package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.character.IceBear;
import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.character.playable.Eskimo;
import hu.minesweepers.modell.character.playable.Explorer;
import hu.minesweepers.modell.command.CommandUtils;
import hu.minesweepers.modell.control.Commandable;
import hu.minesweepers.controller.GameController;
import hu.minesweepers.modell.tile.IceUnit;

/**
 * A statikus osztálynak a felelőssége kezelni az "entity" parancsot, illetve tartalmazza az entitásípusokat (azok az objektumok, amik "élnek", tudnak mozogni a pályán).
 */
public class EntityCommand {

    /**
     * Az entitástípusok felsorolása.
     */
    public enum EntityType {

        /**
         * Az entitástípus eszkimó
         */
        ESKIMO("eskimo"),

        /**
         * Az entitástípus felfedező
         */
        EXPLORER("explorer"),

        /**
         * Az entitástípus jegesmedve
         */
        BEAR("icebear");

        /**
         * Eltárolja az entitástípust stringben.
         */
        private String type;

        /**
         * Az entitástípus konstruktora
         * @param type A stringben kapott entitástípus.
         */
        EntityType(String type) {
            this.type = type;
        }


        /**
         * Átalakítja a stringben kapott entitástípust enum entitástípussá és azt adja vissza.
         * @param type A stringben kapott entitástípust.
         * @return Visszaadja az enum entitástípust. Ha nincs a paraméterrel megegyező entitás, akkor null-t ad vissza.
         */
        public static EntityType fromString(String type) {
            for (EntityType e : EntityType.values()) {
                if (e.type.equalsIgnoreCase(type)) {
                    return e;
                }
            }
            return null;
        }
    }

    /**
     * Ez a függvény oldja meg az "entity" parancsot. A paranccsal létrehozhatunk egy entitást a pályán.
     * @param entityID Az új entitás azonosítója.
     * @param type Az entitás típusa.
     * @param fieldID Az a jégtábla azonosítója, ahova leszeretnénk rakni az új entitást.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítókat vagy entitástípust adtunk meg a parancs argumentumokban.
     */
    public static boolean entity(String entityID, EntityType type, String fieldID) {
        if (type == null) {
            return false;
        }
        IceUnit iu = CommandUtils.getIceUnit(fieldID);
        if (iu == null) {
            return false;
        }
        LocationChanger l = createEntity(entityID, type, -1, -1);
        if (l == null) {
            return false;
        }
        addEntityToIceUnit(entityID, fieldID);
        return true;
    }


    /**
     * Ez a függvény hozzáad egy helyváltoztatásra képes entitást a játékban levő entitáslistához.
     * @param entityID Az új entitás azonosítója.
     * @param type Az entitás típusa.
     * @param bodyHeat Az entitás kezdő testhője.
     * @param stamina Az entitás alap egy körben végezhető műveletek száma.
     * @return Visszaadja a létrehozott objektumot.
     */
    public static LocationChanger createEntity(String entityID, EntityType type, int bodyHeat, int stamina) {
        LocationChanger l = null;
        if (!type.equals(EntityType.BEAR)) {
            Character character = null;
            switch (type) {
                case ESKIMO:
                    Eskimo esk = new Eskimo(5, 4, entityID);
                    if (bodyHeat != -1) {
                        esk.setBodyHeat(bodyHeat);
                    }
                    if (stamina != -1) {
                        esk.setStamina(stamina);
                    }
                    character = esk;
                    CommandUtils.addManipulator(esk);
                    break;
                case EXPLORER:
                    Explorer exp = new Explorer(4, 4, entityID);
                    if (bodyHeat != -1) {
                        exp.setBodyHeat(bodyHeat);
                    }
                    if (stamina != -1) {
                        exp.setStamina(stamina);
                    }
                    character = exp;
                    CommandUtils.addManipulator(exp);
                    break;
            }
            if (character != null) {
                l = character;
                CommandUtils.addCharacter(character);
            }
        } else {
            l = new IceBear(1, entityID);
        }
        if (l != null) {
            CommandUtils.addLocationChanger(l);
        }
        return l;
    }

    /**
     * A függvény felelőssége, hogy lerakja a létrehozott új entitást a kiválasztott jégtáblára.
     * @param entityID Az entitás azonosítója.
     * @param fieldID A jégtábla azonosítója.
     */
    public static void addEntityToIceUnit(String entityID, String fieldID) {
        IceUnit iu = CommandUtils.getIceUnit(fieldID);
        LocationChanger l = CommandUtils.getLocationChanger(entityID);
        if (iu == null || l == null) {
            return;
        }
        l.setIceUnit(iu);
        if (CommandUtils.getCharacters().contains(l)) {
            GameController.getInstance().addCommandable((Commandable) l);
            iu.accept((Character) l);
        }
        else {
            GameController.getInstance().addCommandable((Commandable) l);
            iu.accept((IceBear) l);
        }
    }
}
