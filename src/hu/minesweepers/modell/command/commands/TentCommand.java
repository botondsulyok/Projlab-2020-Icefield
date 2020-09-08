package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.command.CommandUtils;


/**
 * A statikus osztálynak az egyetlen felelőssége kezelni a "tent" parancsot.
 */
public class TentCommand {

    /**
     * Ez a függvény oldja meg a "tent" parancsot. A paranccsal építhetünk egy sátrat a kiválasztott karakterrel arra a jégtáblára, amin éppen áll.
     * @param ID A karakter azonosítója.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítót adtunk meg a parancs argumentumában.
     */
    public static boolean tent(String ID) {
        Character c = CommandUtils.getCharacter(ID);
        if (c != null) {
            c.buildTent();
            return true;
        }
        return false;
    }
}
