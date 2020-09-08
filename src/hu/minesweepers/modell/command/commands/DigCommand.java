package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.command.CommandUtils;


/**
 * A statikus osztálynak az egyetlen felelőssége kezelni a "dig" parancsot.
 */
public class DigCommand {

    /**
     * Ez a függvény oldja meg a "dig" parancsot. A paranccsal egy kiválasztott karakterrel kiáshatunk valamennyi havat, vagy egy tárgyat a jégtábláról.
     * @param ID A karakter azonosítója, akivel szeretnénk ásni.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítót adtunk meg a parancs argumentumában.
     */
    public static boolean dig(String ID) {
        Character c = CommandUtils.getCharacter(ID);
        if (c != null) {
            c.dig();
            return true;
        }
        return false;
    }
}
