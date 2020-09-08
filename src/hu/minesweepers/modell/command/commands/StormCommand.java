package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.Character;
import hu.minesweepers.modell.command.CommandUtils;
import hu.minesweepers.modell.tile.IceUnit;


/**
 * A statikus osztálynak az egyetlen felelőssége kezelni a "storm" parancsot.
 */
public class StormCommand {

    /**
     * Ez a függvény oldja meg a "storm" parancsot. A paranccsal egy viharral rásújthatunk egy karakterre vagy egy jégtáblára.
     * @param ID A karakter vagy a jégtábla azonosítója.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítót adtunk meg a parancs argumentumában.
     */
    public static boolean storm(String ID) {
        Character c = CommandUtils.getCharacter(ID);
        IceUnit iu = CommandUtils.getIceUnit(ID);

        if (c != null) {
            c.struckByDisaster();
        }
        else if (iu != null) {
            iu.struckByDisaster();
        }
        else {
            return false;
        }
        return true;
    }
}
