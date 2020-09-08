package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.movebehaviour.LocationChanger;
import hu.minesweepers.modell.command.CommandUtils;
import hu.minesweepers.modell.tile.IceUnit;


/**
 * A statikus osztálynak az egyetlen felelőssége kezelni a "move" parancsot.
 */
public class MoveCommand {

    /**
     * Ez a függvény oldja meg a "move" parancsot. A paranccsal egy kiválasztott objektummal, ami képes helyváltoztatásra, átléphetünk egy szomszédos jégtáblára.
     * @param entityID Az objektum azonosítója, akivel lépni szeretnénk.
     * @param fieldID A jégtábla azonosítója, ahova lépni szeretnénk.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítókat adtunk meg a parancs argumentumokban.
     */
    public static boolean move(String entityID, String fieldID) {
        LocationChanger l = CommandUtils.getLocationChanger(entityID);
        IceUnit iu = CommandUtils.getIceUnit(fieldID);
        if (l == null || iu == null) {
            return false;
        }
        l.move(iu, l.getIceUnit());
        return true;
    }
}
