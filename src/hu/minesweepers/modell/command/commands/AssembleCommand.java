package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.command.CommandUtils;


/**
 * A statikus osztálynak az egyetlen felelőssége kezelni az "assemble" parancsot.
 */
public class AssembleCommand {

    /**
     * Ez a függvény oldja meg az "assemble" parancsot. A paranccsal egy kiválasztott karakterrel összeszereljük a pisztolyt.
     * @param ID A karakter azonosítója, akivel szeretnénk összerakni a pisztolyt.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítót adtunk meg a parancs argumentumában.
     */
    public static boolean assemble(String ID) {
        Character c = CommandUtils.getCharacter(ID);
        if (c == null) {
            return false;
        }
        if (!c.getComponents().isEmpty()) {
            c.getComponents().get(0).assemble(c);
        }
        return true;
    }
}
