package hu.minesweepers.modell.command.commands;

import hu.minesweepers.modell.character.IceUnitManipulator;
import hu.minesweepers.modell.command.CommandUtils;
import hu.minesweepers.modell.tile.IceUnit;


/**
 * A statikus osztálynak az egyetlen felelőssége kezelni az "ability" parancsot.
 */
public class AbilityCommand {

    /**
     * Ez a függvény oldja meg az "ability" parancsot. A paranccsal egy kiválasztott karakterrel használhatjuk a képességét egy jégtáblán.
     * @param fieldID A jégtábla azonosítója, amin szeretnénk használni a karakter képességét.
     * @param manipulatorID A karakter azonosítója (a karakter képes csak végezni műveletet a jégtáblákon), akivel szeretnénk használni a képességet.
     * @return Visszadja, hogy a parancs sikeresen lefutott vagy sem. Akkor nem sikeres, hogyha nem jó azonosítókat adtunk meg a parancs argumentumokban.
     */
    public static boolean ability(String fieldID, String manipulatorID) {
        IceUnitManipulator m = CommandUtils.getManipulator(manipulatorID);
        IceUnit iu = CommandUtils.getIceUnit(fieldID);
        if (m == null || iu == null) {
            return false;
        }
        m.useAbility(iu);
        return true;
    }
}
